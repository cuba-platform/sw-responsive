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

import com.haulmont.cuba.gui.ComponentsHelper;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.web.gui.components.WebAbstractComponent;
import com.haulmont.cuba.web.toolkit.ui.CubaPlaceHolder;
import com.jarektoro.responsivelayout.ResponsiveColumn;
import org.strangeway.responsive.web.components.ResponsiveLayout;
import org.strangeway.responsive.web.components.ResponsiveLayout.DisplaySize;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static com.haulmont.bali.util.Preconditions.checkNotNullArgument;

public class WebResponsiveColumn extends WebAbstractComponent<ResponsiveColumn>
        implements ResponsiveLayout.Column {

    protected Component childComponent;

    public WebResponsiveColumn() {
        component = new com.jarektoro.responsivelayout.ResponsiveColumn();
    }

    @Override
    public void add(Component childComponent) {
        checkNotNullArgument(childComponent);

        if (this.childComponent != null) {
            throw new IllegalStateException("Column already has component");
        }

        this.childComponent = childComponent;

        this.childComponent.setParent(this);

        component.setComponent(childComponent.unwrap(com.vaadin.ui.Component.class));
    }

    @Override
    public void remove(Component childComponent) {
        if (this.childComponent == childComponent) {
            removeAll();
        }
    }

    @Override
    public void removeAll() {
        if (childComponent != null) {
            component.setComponent(new CubaPlaceHolder());

            this.childComponent = null;
        }
    }

    @Nullable
    @Override
    public Component getOwnComponent(String id) {
        if (Objects.equals(id, childComponent.getId())) {
            return childComponent;
        }

        return null;
    }

    @Nullable
    @Override
    public Component getComponent(String id) {
        return ComponentsHelper.getComponent(this, id);
    }

    @Override
    public Collection<Component> getOwnComponents() {
        if (childComponent != null) {
            return Collections.singletonList(childComponent);
        }

        return Collections.emptyList();
    }

    @Override
    public Collection<Component> getComponents() {
        return ComponentsHelper.getComponents(this);
    }

    @Override
    public void setContent(Component component) {
        removeAll();
        add(component);
    }

    @Override
    public Component getContent() {
        return childComponent;
    }

    @Override
    public void addRule(ResponsiveLayout.Rule rule) {
        component.addRule(
                toInternalDisplaySize(rule.getDisplaySize()),
                rule.getWidth());
    }

    @Override
    public boolean isVisibleForDisplaySize(DisplaySize displaySize) {
        return component.isVisibleForDisplaySize(toInternalDisplaySize(displaySize));
    }

    @Override
    public void setVisibility(DisplaySize displaySize, boolean isVisible) {
        component.setVisibility(toInternalDisplaySize(displaySize), isVisible);
    }

    @Override
    public void setDisplayRules(int xs, int sm, int md, int lg) {
        component.withDisplayRules(xs, sm, md, lg);
    }

    @Override
    public void setContentAlignment(ResponsiveLayout.ContentAlignment componentAlignment) {
        component.setAlignment(ResponsiveColumn.ColumnComponentAlignment.valueOf(componentAlignment.name()));
    }

    @Override
    public void setVisibilityRules(boolean xs, boolean sm, boolean md, boolean lg) {
        component.setVisibilityRules(xs, sm, md, lg);
    }

    @Override
    public void setOffset(DisplaySize displaySize, int width) {
        component.setOffset(toInternalDisplaySize(displaySize), width);
    }

    protected com.jarektoro.responsivelayout.ResponsiveLayout.DisplaySize toInternalDisplaySize(DisplaySize displaySize) {
        return com.jarektoro.responsivelayout.ResponsiveLayout.DisplaySize.valueOf(displaySize.name());
    }
}