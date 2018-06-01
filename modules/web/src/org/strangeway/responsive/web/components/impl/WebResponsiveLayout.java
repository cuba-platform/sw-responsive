/*
 * Copyright (c) 2008-2018 StrangeWay.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.strangeway.responsive.web.components.impl;

import com.haulmont.cuba.web.gui.components.WebAbstractOrderedLayout;
import org.strangeway.responsive.web.components.ResponsiveLayout;

public class WebResponsiveLayout extends WebAbstractOrderedLayout<com.jarektoro.responsivelayout.ResponsiveLayout>
        implements ResponsiveLayout {

    protected ContainerType containerType = ContainerType.FLUID;
    protected boolean scrollable = false;

    public WebResponsiveLayout() {
        component = new com.jarektoro.responsivelayout.ResponsiveLayout();
    }

    @Override
    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;

        component.setContainerType(com.jarektoro.responsivelayout.ResponsiveLayout.ContainerType.valueOf(containerType.name()));
    }

    @Override
    public ContainerType getContainerType() {
        return containerType;
    }

    @Override
    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;

        component.setScrollable(scrollable);
    }

    @Override
    public boolean isScrollable() {
        return scrollable;
    }
}