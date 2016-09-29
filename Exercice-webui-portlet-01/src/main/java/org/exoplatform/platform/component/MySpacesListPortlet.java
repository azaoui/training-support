package org.exoplatform.platform.component;

import java.util.List;

import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.space.SpaceException;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "app:/groovy/spaces/portlet/MySpacesListPortlet/myspacesList.gtmpl"

)
public class MySpacesListPortlet extends UIPortletApplication {
  private SpaceService spaceService = null;

  private String       userId       = null;
  private ConversationState state = ConversationState.getCurrent();

  public MySpacesListPortlet() throws Exception {
    try {
      spaceService = ((SpaceService) getApplicationComponent(SpaceService.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Space> mySpaceList() {
   userId = state.getIdentity().getUserId();
    List<Space> myspaces = null;
    try {
      myspaces = spaceService.getSpaces(userId);
    } catch (SpaceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return myspaces;

  }

}
