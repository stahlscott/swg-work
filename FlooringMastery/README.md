#Flooring Mastery

This was the week 4 capstone project. It's a console-based CRUD that runs a basic Flooring Company order input program. Extra challenge was added by:

1) adding a soft-delete function, writing deleted orders to a hidden file so they could be retrieved if it was deleted by mistake.
2) ability to parse commas inline despite comma being the delimiter (the solution was imperfect, but it was a solution)
3) deleting the order file if no orders were present upon save

But the big challenge came in writing extensive JUnit tests. I had to refactor our original DAOs to make them testable, followed by thoroughly testing every piece and part.
