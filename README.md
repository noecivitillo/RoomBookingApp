# RoomBookingApp

Platform: Android

Language: Kotlin

Min SDK: 17

Target SDK: 27

I started the challenge thinking in the user experience of booking a room, and the time bar component caught my attention, since one of the main functionalities is the time in which a room is available.

This step should be intuitive and easy to use, so I focused my work on creating this component and its logic.

Then, I modeled the data with Parcelable and decided to organize the project following the MVP pattern, I used Dagger2 to inject dependencies and avoided building them in several places.

To handle the calls to the API I used Postman, to make sure that all my data is working as expected. After that I handled the calls with retrofit.

Finally I used Picasso to get the images from the server in a few lines.

