import java.util.*;

public class NFAtoDFA {
    public static void main(String[] args) {
        // Define the NFA
        Set<State> nfaStates = new HashSet<>();
        Set<Character> alphabet = new HashSet<>(Arrays.asList('0', '1'));
        State nfaInitialState = new State("q0");
        Set<State> nfaFinalStates = new HashSet<>(Arrays.asList(new State("q2")));
        Map<Pair<State, Character>, Set<State>> nfaTransitions = new HashMap<>();
        
        nfaStates.add(nfaInitialState);
        nfaStates.add(new State("q1"));
        nfaStates.add(new State("q2"));
        
        nfaTransitions.put(new Pair<>(nfaInitialState, '0'), new HashSet<>(Arrays.asList(nfaInitialState, new State("q1"))));
        nfaTransitions.put(new Pair<>(nfaInitialState, '1'), new HashSet<>(Arrays.asList(new State("q1"))));
        nfaTransitions.put(new Pair<>(new State("q1"), '0'), new HashSet<>(Arrays.asList(new State("q2"))));
        nfaTransitions.put(new Pair<>(new State("q1"), '1'), new HashSet<>(Arrays.asList(nfaInitialState)));
        nfaTransitions.put(new Pair<>(new State("q2"), '0'), new HashSet<>(Arrays.asList(new State("q2"))));
        nfaTransitions.put(new Pair<>(new State("q2"), '1'), new HashSet<>(Arrays.asList(new State("q2"))));

        // Convert NFA to DFA
        Set<Set<State>> dfaStates = new HashSet<>();
        Map<Pair<Set<State>, Character>, Set<State>> dfaTransitions = new HashMap<>();
        Set<State> initialDfaState = epsilonClosure(nfaTransitions, nfaInitialState);
        dfaStates.add(initialDfaState);
        Queue<Set<State>> queue = new LinkedList<>();
        queue.add(initialDfaState);
        
        while (!queue.isEmpty()) {
            Set<State> currentStateSet = queue.poll();
            for (char symbol : alphabet) {
                Set<State> nextStateSet = new HashSet<>();
                for (State state : currentStateSet) {
                    Set<State> nfaNextStates = nfaTransitions.getOrDefault(new Pair<>(state, symbol), new HashSet<>());
                    for (State nfaNextState : nfaNextStates) {
                        nextStateSet.addAll(epsilonClosure(nfaTransitions, nfaNextState));
                    }
                }
                if (!nextStateSet.isEmpty()) {
                    dfaTransitions.put(new Pair<>(currentStateSet, symbol), nextStateSet);
                    if (!dfaStates.contains(nextStateSet)) {
                        dfaStates.add(nextStateSet);
                        queue.add(nextStateSet);
                    }
                }
            }
        }
        
        // Identify final states of DFA
        Set<Set<State>> dfaFinalStates = new HashSet<>();
        for (Set<State> dfaState : dfaStates) {
            for (State nfaFinalState : nfaFinalStates) {
                if (dfaState.contains(nfaFinalState)) {
                    dfaFinalStates.add(dfaState);
                    break;
                }
            }
        }

        // Output DFA representation
        System.out.println("DFA States: " + dfaStates);
        System.out.println("DFA Alphabet: " + alphabet);
        System.out.println("DFA Initial State: " + initialDfaState);
        System.out.println("DFA Final States: " + dfaFinalStates);
        System.out.println("DFA Transitions: " + dfaTransitions);
    }
    
    // Helper method to compute epsilon closure
    private static Set<State> epsilonClosure(Map<Pair<State, Character>, Set<State>> transitions, State state) {
        Set<State> closure = new HashSet<>();
        Stack<State> stack = new Stack<>();
        stack.push(state);
        while (!stack.isEmpty()) {
            State currentState = stack.pop();
            closure.add(currentState);
            Set<State> epsilonTransitions = transitions.getOrDefault(new Pair<>(currentState, null), new HashSet<>());
            for (State nextState : epsilonTransitions) {
                if (!closure.contains(nextState)) {
                    stack.push(nextState);
                }
            }
        }
        return closure;
    }
}

class State {
    private String name;
    
    public State(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}

class Pair<T, U> {
    private T first;
    private U second;
    
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() {
        return first;
    }
    
    public U getSecond() {
        return second;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }
}
