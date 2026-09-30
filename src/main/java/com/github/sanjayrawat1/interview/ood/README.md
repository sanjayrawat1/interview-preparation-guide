It's perfectly normal not to have deep domain knowledge about every system an interviewer might throw at you. The good news is that interviewers usually aren't testing your encyclopedic knowledge of specific industries, but rather your ability to analyze, ask intelligent questions, and apply design principles to an unknown problem.

Here's how you can approach it when you genuinely don't have much idea about the system being asked:

**1. Don't Panic and Be Honest (but Positive)**
* **Acknowledge**: "That's an interesting system! While I might not have deep domain expertise in [X domain], I'm confident I can apply general design principles to break it down. To ensure I'm building what you're looking for, could we start by clarifying a few fundamental aspects?"
* **Avoid Bluffing**: Never pretend to know something you don't. It will quickly become evident and can hurt your credibility.

**2. Deconstruct the Prompt's Keywords**
Even if you don't know the system, you know the words in the prompt. Break them down.
Let's stick with "Movie Ticket Booking System" as an example:
* **"Movie"**: What's a movie in this context? What information does it need (title, genre, duration, cast)?
* **"Ticket"**: What does a ticket represent? What information should it contain (for what, when, where, for whom, how much)?
* **"Booking"**: What does "booking" imply? Selecting, reserving, purchasing, confirming, possibly cancelling?
* **"System"**: This implies a software application, likely with a user interface, data storage, and various operations.

**3. Ask General-Purpose Questions Applicable to ANY System**
These questions help you establish the foundational understanding for any design, regardless of domain:

**A. User-Centric Questions:**

* "Who are the primary users of this system? (e.g., end-users, administrators, staff, external systems) What are their main goals?"
* "Can you describe a typical user journey or a core use case for this system? For example, if an end-user wants to achieve [main goal from prompt], what steps would they take?" (This is excellent for building context).

**B. Core Purpose & Scope Questions:**

* "What is the absolute most critical function this system must perform?"
* "What problem is this system primarily designed to solve?"
* "Are there any features we should explicitly include or exclude for the scope of this interview (e.g., payment processing, user authentication, reporting, notifications, cancellations)?" This is crucial for managing your time.
* "Is this a new system, or are we integrating with existing systems? If so, which ones?"

**C. Data-Centric Questions:**

* "What are the main types of entities or information this system needs to manage and store?" (This helps you identify your core objects early).
* "What are the relationships between these main entities?"

**D. Non-Functional Requirements: (These are almost always relevant)**

* "Are there any specific performance requirements? (e.g., how fast should searches be, how many concurrent users do we need to support?)"
* "What about scalability? Do we expect this system to grow significantly?"
* "What are the security considerations?"
* "How important is reliability and availability?"

**4. Use Analogies (Carefully)**
* If the system is completely alien, try to think of related systems you do know.
* For "Movie Ticket Booking," if you knew nothing about movies, you could still relate it to "Event Booking," "Restaurant Reservations," or "Flight Booking."
* You could ask: "Is this system conceptually similar to a [related system you know], or are there unique aspects of [the new domain] that I should be aware of?"
* Be cautious: Don't assume too much from the analogy, just use it as a starting point for further questions.
   
**Example Walkthrough (if you knew nothing about "Movie Ticket Booking"):**

**Interviewer:** "Design a Movie Ticket Booking System."

**You:** "Okay, that sounds interesting! To make sure I cover the most important aspects for this interview, I'd love to clarify a few things since I don't have deep domain expertise in the cinema industry.

1. Users & Goal: Who are the primary users of this system? Is it mainly for customers to buy tickets, or does it also need to support cinema staff managing 
schedules or inventory? What's the core problem this system is trying to solve for them?
2. Core Entities: When you say 'movie ticket booking,' what are the main 'things' (entities) involved? For example, what kind of information do we need to 
   store about a 'movie'? And what does a 'ticket' represent – is it tied to a specific seat, time, and movie?
3. Core Process: Could you walk me through a basic scenario? For instance, if a customer wants to book a ticket, what's the typical flow from their perspective?
4. Scope Boundaries: For the purpose of this design exercise, should I consider things like payment processing, user authentication, managing different cinema 
   locations, or seat pricing tiers? Or can we focus on the core booking logic first?
5. Non-Functional Aspects: Are there any particular performance expectations, like how quickly search results should appear, or concerns about concurrent 
   bookings?"

By asking these types of questions, you demonstrate your analytical skills, your ability to break down a complex problem, and your structured approach to design, even when facing an unfamiliar domain. The interviewer will appreciate your proactive approach to understanding the problem.