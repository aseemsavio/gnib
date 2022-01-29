
# This will pull the image for the arm64/arch64 architecture.
# When updating this file, make sure to add the latest tag and the digest of 
# the corresponding image for the OS/ARCH set as linux/arm64/v8.
FROM ubuntu:latest@sha256:64162ac111b666daf1305de1888eb67a3033f62000f5ff781fe529aff8f88b09

# Sets up the environmental variables in advance.
ENV JAVA_HOME "/usr/lib/jvm/graalvm-ce-java11-22.0.0.2"
ENV PATH "${JAVA_HOME}/bin:${PATH}"

# Installs the pre-requisites in the bash terminal.
RUN bash -c 'apt-get update && \
    apt-get install gcc zlib1g-dev build-essential -y && \
    apt-get install wget -y && \
    apt-get install tar -y'

# Installs the Java (GraalVM runtime) related dependencies in the bash terminal.
RUN bash -c 'wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.0.0.2/graalvm-ce-java11-linux-aarch64-22.0.0.2.tar.gz && \
    tar -xvzf graalvm-ce-java11-linux-aarch64-22.0.0.2.tar.gz && \
    mkdir /usr/lib/jvm && \
    mv graalvm-ce-java11-22.0.0.2/ /usr/lib/jvm && \
    rm graalvm-ce-java11-linux-aarch64-22.0.0.2.tar.gz && \
    java -version && \
    gu install native-image'
