package com.faforever.client.mod;

import com.faforever.client.i18n.I18n;
import com.faforever.client.notification.NotificationService;
import com.faforever.client.reporting.ReportingService;
import com.faforever.client.util.IdenticonUtil;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.function.Consumer;

public class ModTileController {

  @FXML
  Label commentsLabel;
  @FXML
  ImageView thumbnailImageView;
  @FXML
  Label nameLabel;
  @FXML
  Label authorLabel;
  @FXML
  Label likesLabel;
  @FXML
  Node modTileRoot;

  @Resource
  ModService modService;
  @Resource
  NotificationService notificationService;
  @Resource
  I18n i18n;
  @Resource
  ReportingService reportingService;

  private ModInfoBean mod;
  private Consumer<ModInfoBean> onOpenDetailListener;

  public void setMod(ModInfoBean mod) {
    this.mod = mod;
    Image image;
    if (StringUtils.isNotEmpty(mod.getThumbnailUrl())) {
      image = new Image(mod.getThumbnailUrl());
    } else {
      image = IdenticonUtil.createIdenticon(mod.getId());
    }
    thumbnailImageView.setImage(image);
    nameLabel.setText(mod.getName());
    authorLabel.setText(mod.getAuthor());
    likesLabel.setText(String.format("%d", mod.getLikes()));
    commentsLabel.setText(String.format("%d", mod.getComments().size()));

    modService.getInstalledMods().addListener((ListChangeListener<ModInfoBean>) change -> {
      while (change.next()) {
        for (ModInfoBean modInfoBean : change.getAddedSubList()) {
          if (mod.getId().equals(modInfoBean.getId())) {
            setInstalled(true);
            return;
          }
        }
        for (ModInfoBean modInfoBean : change.getRemoved()) {
          if (mod.getId().equals(modInfoBean.getId())) {
            setInstalled(false);
            return;
          }
        }
      }
    });
  }

  private void setInstalled(boolean installed) {

  }

  public Node getRoot() {
    return modTileRoot;
  }

  public void setOnOpenDetailListener(Consumer<ModInfoBean> onOpenDetailListener) {
    this.onOpenDetailListener = onOpenDetailListener;
  }

  @FXML
  void onShowModDetail() {
    onOpenDetailListener.accept(mod);
  }
}
