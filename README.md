# SW-responsive

Responsive Layout for CUBA Platform

<a href="http://www.apache.org/licenses/LICENSE-2.0"><img src="https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat" alt="license" title=""></a>
[![Download](https://api.bintray.com/packages/cuba-platform/main/sw-responsive/images/download.svg) ](https://bintray.com/cuba-platform/main/sw-responsive/_latestVersion)
[![Build Status](https://travis-ci.org/cuba-platform/sw-responsive.svg?branch=master)](https://travis-ci.org/strangeway-org/sw-responsive)

Based on Responsive Layout Vaadin Add-on: https://github.com/JarekToro/responsive-layout

<h1 align="center"><img src="https://github.com/strangeway-org/sw-responsive/blob/master/doc/sw-responsive.gif" alt="Demo" align="center">
</h1>

## Installation

The add-on can be added to your project in one of the ways described below. Installation from the Marketplace is the simplest way. The last version of the add-on compatible with the used version of the platform will be installed.
Also, you can install the add-on by coordinates choosing the required version of the add-on from the table.

In case you want to install the add-on by manual editing or by building from sources see the complete add-ons installation guide in [CUBA Platform documentation](https://doc.cuba-platform.com/manual-latest/manual.html#app_components_usage).

### From the Marketplace

1. Open your application in CUBA Studio. Check the latest version of CUBA Studio on the [CUBA Platform site](https://www.cuba-platform.com/download/previous-studio/).
2. Go to *CUBA -> Marketplace* in the main menu.

 ![marketplace](doc/marketplace.png)

3. Find the *SW-responsive* add-on there.

 ![addons](doc/addons.png)

4. Click *Install* and apply the changes.
The add-on corresponding to the used platform version will be installed.

### By Coordinates

1. Open your application in CUBA Studio. Check the latest version of CUBA Studio on the [CUBA Platform site](https://www.cuba-platform.com/download/previous-studio/).
2. Go to *CUBA -> Marketplace* in the main menu.
3. Click the icon in the upper-right corner.

 ![by-coordinates](doc/by-coordinates.png)

4. Paste the add-on coordinates in the corresponding field as follows:

 `org.strangeway.responsive:sw-responsive-global:<add-on version>`

 where `<add-on version>` is compatible with the used version of the CUBA platform.

 | Platform Version | Add-on Version |
|------------------|----------------|
| 7.2.x          | 1.5.0          |
| 7.1.x          | 1.4.0          |
| 7.0.x          | 1.3.0          |
| 6.10.x         | 1.2.0          |
| 6.9.x          | 1.1.0          |
| 6.8.x          | 1.0.0          |

5. Click *Install* and apply the changes. The add-on will be installed to your project.

## Quick Start

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
