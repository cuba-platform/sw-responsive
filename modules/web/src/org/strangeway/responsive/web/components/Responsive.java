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

package org.strangeway.responsive.web.components;

import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("c10m_Responsive")
public class Responsive {
    @Inject
    protected ComponentsFactory componentsFactory;

    public ResponsiveLayout newLayout() {
        return (ResponsiveLayout) componentsFactory.createComponent(ResponsiveLayout.NAME);
    }

    public ResponsiveLayout.Row newRow() {
        return (ResponsiveLayout.Row) componentsFactory.createComponent(ResponsiveLayout.Row.NAME);
    }

    public ResponsiveLayout.Column newColumn() {
        return (ResponsiveLayout.Column) componentsFactory.createComponent(ResponsiveLayout.Column.NAME);
    }
}