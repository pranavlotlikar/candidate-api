# Spring Boot API Demo (Candidate API)

> This is an API demo using Spring Boot. Below are the basic features of this API:

### Create a candidate:
> The _entercandidate_ API takes a name as a parameter and saves that with a vote count initialized to 0.

> Eg. http://localhost:8080/entercandidate?name=Mohan

### Cast a vote:
> The _castvote_ API takes a name as a parameter, increasing the vote count for that candidate, and returns it. 

> Votes are updated only for existing candidates, otherwise, validation messages are returned.

> Eg. http://localhost:8080/castvote?name=Mohan

### Get vote count:
> The _countvote_ API takes an existing candidate's name as a parameter and returns the latest vote count.

> For invalid parameters, validation messages are returned.

> Eg. http://localhost:8080/countvote?name=Mohan

### List all candidates:
> The _listvote_ API returns all candidate names and their vote counts.

> Eg. http://localhost:8080/listvote

### Get the winner:
> The _getwinner_ API returns the name of the candidate with the maximum number of votes.

> If there are several candidates with maximum votes, they're all returned.

> Eg. http://localhost:8080/getwinner
