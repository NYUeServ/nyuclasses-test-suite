#Start with a Linux micro-container
FROM maven:3.3.9-jdk-8

# Google Chrome
#
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
	&& echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
	&& apt-get update -qqy \
	&& apt-get -qqy install google-chrome-stable \
	&& rm /etc/apt/sources.list.d/google-chrome.list \
	&& rm -rf /var/lib/apt/lists/* /var/cache/apt/* \
	&& sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome

# ChromeDriver

ARG CHROME_DRIVER_VERSION=2.31
RUN wget --no-verbose -O /tmp/chromedriver_linux64.zip https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip \
	&& rm -rf /opt/chromedriver \
	&& unzip /tmp/chromedriver_linux64.zip -d /opt \
	&& rm /tmp/chromedriver_linux64.zip \
	&& mv /opt/chromedriver /opt/chromedriver-$CHROME_DRIVER_VERSION \
	&& chmod 755 /opt/chromedriver-$CHROME_DRIVER_VERSION \
	&& ln -fs /opt/chromedriver-$CHROME_DRIVER_VERSION /usr/bin/chromedriver

# Firefox
ENV FIREFOX_VERSION 56.0
ENV FIREFOX_DIR /usr/bin/firefox
ENV FIREFOX_FILENAME $FIREFOX_DIR/firefox.tar.bz2
ENV PATH $FIREFOX_DIR:$PATH
RUN mkdir $FIREFOX_DIR \
	&& wget -q --continue --output-document $FIREFOX_FILENAME "https://ftp.mozilla.org/pub/firefox/releases/${FIREFOX_VERSION}/linux-x86_64/en-US/firefox-${FIREFOX_VERSION}.tar.bz2" \
	&& tar -xaf "$FIREFOX_FILENAME" --strip-components=1 --directory "$FIREFOX_DIR"

# Xvfb

RUN apt-get update -qqy \
	&& apt-get -qqy install xvfb \
	&& rm -rf /var/lib/apt/lists/* /var/cache/apt/*

#Move your test cases to the container filesystem
ADD ./ /test

RUN ls -lrt /test/


#Setting default working directory
WORKDIR /test

#Verify installation in logs
RUN mvn -version

# Run a Virtual Buffer to be able to Display the chrome browser
RUN Xvfb :99 -screen 0 1680x1050x24 &
RUN export DISPLAY=:99

ENTRYPOINT ["mvn", "clean", "test"]