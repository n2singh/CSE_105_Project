### **Task 2: Illustrating a theorem** In this part of the project, you’ll choose a pattern in an application you care about, define it precisely, build a DFA that recognizes it, and then build the related Turing machine that proves that the language encoding this pattern is decidable.

First, pick one application for your example. Here are some ideas to get you started - but you can choose to go in a different direction.

- Data validation for input in text files (e.g. emails with specific domains, dates in specific formats, PIDs in a class list, etc.)
- Finding ASCII codes for punctuation in a binary file.
- The CDC recommended procedure for hand washing (Refer to the guidelines from the CDC - here https://www.cdc.gov/handwashing/index.html in your explanation. You might find the first example in chapter 1 about automatic door controllers helpful when starting your design.)
- See more ideas here: [https://theory-cs.github.io/files/practical-applications-of-theory-ofcomputation.pdf](https://theory-cs.github.io/files/practical-applications-of-theory-ofcomputation.pdf)

**Then:**

1. In a paragraph or so, give the context for your chosen application and why you chose it.
2. Specify the alphabet for your example and write a precise (mathematical and/or English) description of a set of strings over this alphabet that is relevant to this application, and include a sentence or so justifying why this set is important and relevant.
3. Give one example of a string over this alphabet in this set and a string over this (same) alphabet not in this set, and explain why you chose these example strings.
4. Design a DFA that recognizes this language. Clearly draw and label the state diagram of this DFA and briefly justify why your design works by describing the role of each state of the DFA and relating it to a plain English description of the language you picked.
5. Use the construction in the proof that all regular languages are decidable (informally described in Theorem 4.1 in the book) to build a Turing machine that simulates your DFA. Hint: your Turing machine will have exactly two more states than your DFA. Draw the state diagram of your Turing machine.
6. For one of the strings from step 4., draw a representation of the computation of your Turing machine on this string. Remember that to describe the computation of a Turing machine, we need to include the contents of the tape, the state of the machine, and the location of the read/write head at each step in the computation. In class we’ve drawn pictures to represent the configuration of the machine at each step in a computation. You may do so or you may choose to describe these configurations in words.

**Checklist for submission**

Solution typed or clearly hand-written/drawn with precise language and notation for all terms and complete, correct, and clear justification.
Each of the six steps is complete and included in the PDF, with precise language and notation for all terms and complete, correct, and clear justification.
