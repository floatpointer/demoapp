# Todo DemoApp

This is a small TODO application.
There's two parts to this application:

## Backend 
Spring-boot based backend application with a very crude "database".
 
Start the server by running (requires Maven): 
`mvn package`
`mvn spring-boot:run`

If the server is running you should be able to test it through your browser at `http://localhost:8080`

#### Known issues:
Gave up the fight with CORS and the delete method. Hence the DELETE function has been implemented as a GET.
Test only has a single integration test.

## Frontend
Front end was developed using NodeJS/Vue.
Hence Node is a pre-requisite.
Might not be the most estetically pleasing app there is, but considering the fact that I've never ever written a single line of Vue code I think the result is acceptable.

Run `npm install` and then `npm run serve` to start the app.
`http://localhost:9090` is where you'll find it.

#### Known issues:
Can't get rid of the no-mixed-spaces-and-tabs whining.
There's no front-end unit testing. If time would have permitted I'd add some Jasmine tests.
There's no unit testing for the front-end. But 
