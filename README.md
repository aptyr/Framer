Framer
===

Library allows to play animation from XML file - `animation-list`. Framer doesn't load all items from XML file at once and less memory is allocated. Animation can be played as many times as you like (1,2,3... to infinity).

## Getting Started

### 1. Install
- Maven
```xml
<dependency>
  <groupId>com.aptyr.framer</groupId>
  <artifactId>framer</artifactId>
  <version>0.3</version>
  <type>pom</type>
</dependency>
```
- Gradle
```groovy
compile 'com.aptyr.framer:framer:0.3'
```

### 2. How to use
- Inside XML layout file.
```xml
<com.aptyr.framer.Framer
        android:id="@+id/framer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
```

- By inheritance.
```java
class YourClass extends Framer { }
```

### 3. Player delegate

- Called when animation is finished.
```java
void onPlayerCompleted(@DrawableRes int resID) {}
```
- Called when error occurs while setting frame.
```java
void onPlayerError(@DrawableRes int resID, Throwable e) {}
```
- Called before displaying frame.
```java
void onPlayerCurrentFrame(@DrawableRes int resID, Frame frame) {}
```
License
===

    Copyright (C) 2016 Aptyr (github.com/aptyr)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
