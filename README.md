# kashafgateway

This is a "gateway" application intended to be part of a microservice architecture.

This application is configured for Service Discovery and Configuration with the Registry. On launch, it will refuse to
start if it is not able to connect to the Registry at [http://localhost:8761](http://localhost:8761).

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project. Depending on your system, you can
   install Node either from source or as a pre-packaged bundle.
2. [Yarn][]: We use Yarn to manage Node dependencies. Depending on your system, you can install Yarn either from source
   or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools. You will only need
to run this command when dependencies change in [package.json](package.json).

    yarn install

We use [Gulp][] as our build system. Install the Gulp command-line tool globally with:

    yarn global add gulp-cli

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    gulp

[Bower][] is used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [bower.json](bower.json). You can also run `bower update` and `bower install` to manage
dependencies. Add the `-h` flag on any command to see how you can use it. For example, `bower update -h`.

For further instructions on how to develop with JHipster, have a look at [Using in development][].

## Building for production

To optimize the kashafgateway application for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references
these new files. To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Testing

To launch your application's tests, run:

    ./mvnw clean test

### Client tests

Unit tests are run by [Karma][] and written with [Jasmine][]. They're located
in [src/test/javascript/](src/test/javascript/) and can be run with:

    gulp test

For more information, refer to the [Running tests page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your development experience. A number of docker-compose configuration are available in
the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a oracle database in a docker container, run:

    docker-compose -f src/main/docker/oracle.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/oracle.yml down

You can also fully dockerize your application and all the services that it depends on. To achieve this, first build a
docker image of your app by running:

    ./mvnw verify -Pprod dockerfile:build

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the
docker-compose sub-generator (`docker-compose`), which is able to generate docker configurations for one or several
applications.

[Node.js]: https://nodejs.org/

[Yarn]: https://yarnpkg.org/

[Bower]: http://bower.io/

[Gulp]: http://gulpjs.com/

[BrowserSync]: http://www.browsersync.io/

[Karma]: http://karma-runner.github.io/

[Jasmine]: http://jasmine.github.io/2.0/introduction.html

[Protractor]: https://angular.github.io/protractor/

[Leaflet]: http://leafletjs.com/

[DefinitelyTyped]: http://definitelytyped.org/
