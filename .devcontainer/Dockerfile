FROM mcr.microsoft.com/devcontainers/java:0-17
	
# Install additional tools and packages
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    gradle \
    git \
    curl \
    wget \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*


# Set JAVA_HOME environment variable
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Set the working directory in the container
WORKDIR /workspace

# Optionally, copy your project files into the container
# COPY . /workspace