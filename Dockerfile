
# This will pull the image for the arm64/arch64 architecture.
# When updating this file, make sure to add the latest tag and the digest of 
# the corresponding image for the OS/ARCH set as linux/arm64/v8.
FROM ubuntu:latest@sha256:64162ac111b666daf1305de1888eb67a3033f62000f5ff781fe529aff8f88b09

# Updates the environment.
RUN apt-get update

# Installs the essentials, prrequisites for Graal VM.
RUN apt-get install gcc zlib1g-dev build-essential -y
RUN apt-get install wget -y
RUN apt-get install tar -y

# Downloads GRAALVM source files from the official Git repo.
RUN wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.0.0.2/graalvm-ce-java11-linux-aarch64-22.0.0.2.tar.gz

# Extracts the source files.
RUN tar -xvzf graalvm-ce-java11-linux-aarch64-22.0.0.2.tar.gz

# Creates a directory and moves the extracted files from above.
RUN mkdir /usr/lib/jvm
RUN mv graalvm-ce-java11-22.0.0.2/ /usr/lib/jvm

# Specifies path and JAVA_HOME
RUN echo 'export PATH=/usr/lib/jvm/graalvm-ce-java11-22.0.0.2/bin:$PATH' >> ~/.bashrc
RUN echo 'export JAVA_HOME=/usr/lib/jvm/graalvm-ce-java11-22.0.0.2' >> ~/.bashrc
RUN source ~/.bashrc

# Installs native-image
RUN gu install native-image

