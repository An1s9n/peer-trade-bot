# peer-trade-bot

Peer trade bot helps you to get simple Telegram notifications about Binance P2P trades you are interested in. Bot
repeatedly polls Binance API for available P2P trades and notifies you in Telegram chat when desired conditions reached
or changed. See configuration properties in [application.yaml](src/main/resources/application.yaml)

### Implementation details

Peer trade bot is a [Quarkus](https://quarkus.io)-based application written in
pure [Kotlin](https://kotlinlang.org). [Gradle](https://gradle.org) is used as a build
tool. [Dockerfile](Dockerfile.native-micro) is used in order to build a container that runs the Quarkus application in
native (no JVM) mode. It uses a micro base image, tuned for Quarkus native executables. It reduces the size of the
resulting container image

### How to use

In order to launch bot on your machine you only need to have [Docker](https://www.docker.com) installed. Build Docker
image by running

```sh 
docker build -f Dockerfile.native-micro -t ru.an1s9n/peer-trade-bot:1.0.0 .
```

Prepare `application.yaml` configuration file, put it into [config](config) folder (create it) and
use [compose.yaml](compose.yaml) to
launch the bot (see
[docker compose reference](https://docs.docker.com/compose/reference) for details). That`s it!
