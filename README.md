# sw-responsive

Responsive Layout for CUBA Platform

<a href="http://www.apache.org/licenses/LICENSE-2.0"><img src="https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat" alt="license" title=""></a>
[![Download](https://api.bintray.com/packages/strangeway-org/libs/sw-responsive/images/download.svg) ](https://bintray.com/strangeway-org/libs/sw-responsive/_latestVersion)
[![Build Status](https://travis-ci.org/strangeway-org/sw-responsive.svg?branch=master)](https://travis-ci.org/strangeway-org/sw-responsive)

Based on Responsive Layout Vaadin Add-on: https://github.com/JarekToro/responsive-layout

<h1 align="center"><img src="https://github.com/strangeway-org/sw-responsive/blob/master/doc/sw-responsive.gif" alt="Demo" align="center">
</h1>

### Usage
Select a version of the add-on which is compatible with the platform version used in your project:

| Platform Version| Add-on Version|
|:----------------|:--------------|
| 6.10.0+         | 1.2+          |
| 6.9.0+          | 1.1+          |
| 6.8.5+          | 1.0+          |

Add custom application component to your project (change the version part if needed):
```
org.strangeway.responsive:sw-responsive-global:1.2.0
```

### Quick Start

Let's say you want to implement responsive dashboard in a screen.

First of all, add xmlns `http://strangeway.org/xsd/responsive/sw-responsive.xsd` reference to the screen XML:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<window xmlns="http://schemas.haulmont.com/cuba/window.xsd"
        caption="msg://caption"
        xmlns:swr="http://strangeway.org/xsd/responsive/sw-responsive.xsd"
        class="com.company.demo.web.screens.Dashboard"
        messagesPack="com.company.demo.web.screens">
    <layout>

    </layout>
</window>
```

The main UI component of the app component is `ResponsiveLayout`. Let's define one:
```xml
<swr:responsiveLayout width="100%">

</swr:responsiveLayout>
```

`ResponsiveLauyout` may contain only row components. We will have 2 rows in this example:
```xml
<swr:responsiveLayout width="100%">
    <swr:row>
        
    </swr:row>
    <swr:row>
        
    </swr:row>
</swr:responsiveLayout>
```

Each row may contain one or more columns:
```xml
<swr:responsiveLayout width="100%">
    <swr:row>
        <swr:column>

        </swr:column>
        <swr:column>

        </swr:column>
        <swr:column>

        </swr:column>
        <swr:column>

        </swr:column>
    </swr:row>
    <swr:row>
        <swr:column>
                        
        </swr:column>
    </swr:row>
</swr:responsiveLayout>
```

Column elements define layout constraints for different resolutions:
```xml
<swr:column>
    <swr:content>
        <groupBox caption="Area"
                  height="150px"
                  width="100%">
            <label stylename="big-number"
                   align="MIDDLE_CENTER"
                   value="541.382 km^2"/>
        </groupBox>
    </swr:content>

    <swr:display xs="12" sm="6" md="6" lg="3"/>
</swr:column>
```

Here we set 12 columns width for XS resolution (mobile) and only 3 columns width for large resolutions (desktop).

Row elements have additional options, such as `minHeight`, `maxHeight`, `shrink`, `grow`, etc:
```xml
<swr:row stylename="map-row"
         margin="false"
         minHeight="50vh"
         grow="true">
    <swr:column height="100%">
        <swr:content>
            <browserFrame width="100%"
                          height="100%">
                <url url="https://en.wikipedia.org/wiki/Samara#/media/File:Outline_Map_of_Samara_Oblast.svg"/>
            </browserFrame>
        </swr:content>

        <swr:display xs="12" sm="12" md="12" lg="12"/>
    </swr:column>
</swr:row>
```

Finally, we could create simple responsive dashboard:

<h1 align="center"><img src="https://github.com/strangeway-org/sw-responsive/blob/master/doc/demo.gif" alt="Demo" align="center">
</h1>

See demo project: https://github.com/strangeway-org/sw-responsive-demo


### Read more

See also the original docs on Grid System from Vaadin Add-on: 
https://github.com/JarekToro/responsive-layout/wiki/The-Grid-System
