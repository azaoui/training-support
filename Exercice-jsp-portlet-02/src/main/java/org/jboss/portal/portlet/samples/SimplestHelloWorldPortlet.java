/******************************************************************************
 * JBoss, a division of Red Hat                                               *
 * Copyright 2008, Red Hat Middleware, LLC, and individual                    *
 * contributors as indicated by the @authors tag. See the                     *
 * copyright.txt in the distribution for a full listing of                    *
 * individual contributors.                                                   *
 *                                                                            *
 * This is free software; you can redistribute it and/or modify it            *
 * under the terms of the GNU Lesser General Public License as                *
 * published by the Free Software Foundation; either version 2.1 of           *
 * the License, or (at your option) any later version.                        *
 *                                                                            *
 * This software is distributed in the hope that it will be useful,           *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU           *
 * Lesser General Public License for more details.                            *
 *                                                                            *
 * You should have received a copy of the GNU Lesser General Public           *
 * License along with this software; if not, write to the Free                *
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA         *
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.                   *
 ******************************************************************************/
package org.jboss.portal.portlet.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.space.SpaceException;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;


public class SimplestHelloWorldPortlet extends GenericPortlet {
  public void doView(RenderRequest request, RenderResponse response) throws IOException {
    PrintWriter writer = response.getWriter();
    ExoContainer container = ExoContainerContext.getCurrentContainer();
    SpaceService spaceService = (SpaceService) container.getComponentInstance(SpaceService.class);
    ConversationState state = ConversationState.getCurrent();
    String userId = state.getIdentity().getUserId();
    List<Space> myspaces = null;
    try {
      myspaces = spaceService.getSpaces(userId);
    } catch (SpaceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    writer.print("<ul>");
    for (Space space : myspaces) {
      writer.print("<li>" + space.getDisplayName() + "</li>");

    }

    writer.close();

    }
}
