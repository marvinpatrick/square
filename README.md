## Build tools & versions used

1. GitHub Actions
2. Snyk
3. KMMViewModel
4. Koin
5. Ktor
6. Coil

For each of the above tools/libraries I used the most recent version. As often as I can, I avoid
using old versions of libraries when making new projects. I was able to do so successfully and able
to feel confident there are no vulnerabilities.

## Steps to run the app

1. Providing there are no configuration changes, you can select the androidApp/iosApp configurations
   to deploy on either device.

## What areas of the app did you focus on?

When deciding how to approach new projects, I stick to the time proven strategies of shift-left
testing in conjunction with continuous integration. I start by setting up GitHub Actions to
automate unit testing. I do this because of what I learned in "Test-Driven Development by Example","
Growing Object Oriented Software Guided by Tests". I decided to build a KMM Mobile App for two
reasons. First, I wanted to differentiate myself as a candidate, and secondly to reveal what I find
to be my greatest strength:my willingness to keep my finger to the pulse of the latest
Android/Kotlin capabilities. As with most applications built today, there came a need for third
party libraries. Before adding any libraries I incorporate Snyk security testing to ensure the
libraries I do use aren't exposing our users to any vulnerabilities and be alerted if it ever
changes. This decision was inspired by "Full Stack Testing"

After finishing what I've come to know as Iteration Zero from "Code That Fits in Your Head". I then
proceeded to implement the acceptance criteria.

1. KMM
2. TDD & Continuous Integration
3. Shift-Left Unit Testing
4. Shift-Left Security Testing
5. Anti-Corruption Layer inspired architecture to clearly separate the business solution code and
   the
   library dependent code.
6. Bounded Context inspired package structure to increase cohesion and decrease coupling of related
   components and allowing the navigation of the app to be in parity with the navigation of the code
   base.
7. Fundamental coding practices such as descriptive naming, proper encapsulation, and SOLID
   principles.
8. Demonstrating the use of Design Patterns when appropriate.

## What was the reason for your focus? What problems were you trying to solve?

I decided to 7 hours and 36 minutes exactly on this project. I

## How long did you spend on this project?

I spent 7 hours and 36 minutes exactly on this project. Given the opportunity to become a Square I
saw it best to utilize as much time as allotted and building this project was fun!

## Did you make any trade-offs for this project? What would you have done differently with more time?

1. As I was developing this project I couldn't find a reliable library to mock KMM projects. I found
   MockKMP but noticed that there is a need to call a function of theres titled installWorkaround().
   Weighing the pros/cons of relying on what seems to be an unstable library or custom code, I
   decided to go with custom code. My reasoning is that with either design I'll be pushing data to
   the view model to test business logic. So as long as I was hardcoding test data it doesn't matter
   how the view model gets it. The benefit is that we can get started with unit test and don't have
   to rely on a third-party library. The potential problem was class bloat as we'd need to create
   many classes to cover many use cases. I can instead just create an interface for the Repo. Have a
   RepoImpl for usage and a RepoFake for testing. I don’t see much being traded in terms of ‘when’
   ‘return’ in mocking and using fakes.
2. With more time, I would include more data from the Employee data class to render on screen. I
   would also do a bit more UI work on the Empty and Error State UI. 

## What do you think is the weakest part of your project?

I believe the weakest part of this project is the minor details. I focused on high level decisions
to get started and prepared for any extension. With this, I did skip over some smaller details.
Details such as the choice to use hard coded string values. I also got into the flow and abandoned
separation of concerns in the EmployeeDirectoryScreen. Although it is still a short file, I'd prefer
to have broken it down more. I also decided that a generic catch all exception for error handling
would be appropriate for the time being.

## Did you copy any code or dependencies? Please make sure to attribute them here!

1. I never had a requirement to check for memory usage before making an API call. I was relieved
   that it was rather simple and I used the code sample from the official documentation and just
   needed to make tweaks discovered through trial and
   error. [https://developer.android.com/topic/performance/memory]
2. I also never packaged up a project this way, hope I got it right! I of course used the git
   command provided in the instructions as well as the git command listed as a comment under the
   approved solution at this StackOverflow
   post [https://stackoverflow.com/questions/55515678/how-do-i-export-my-project-as-a-zip-of-git-repository]
3. I drew inspiration from a side project I'm working. I often work on side projects because it
   affords me the opportunity to be exposed to problems that I might not otherwise be exposed to at
   work. I also like to test my own ideologies of how software should be built and put them to test.

## Is there any other information you’d like us to know?

For this project I decided to focus more on the breadth of knowledge rather than the depth of
knowledge. I wanted to show the wide array of topics I've been exposed to and can confidently
implement and teach. I had a really great time building it and look forward to the next step in the
process. Lastly, I did differentiate between the UI iPhone app and Android to improve the UX. =)
