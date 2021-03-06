package com.faforever.client.config;

import com.faforever.client.ThemeService;
import com.faforever.client.ThemeServiceImpl;
import com.faforever.client.achievements.AchievementService;
import com.faforever.client.achievements.AchievementServiceImpl;
import com.faforever.client.api.FafApiAccessor;
import com.faforever.client.api.FafApiAccessorImpl;
import com.faforever.client.api.LocalHttpVerificationCodeReceiver;
import com.faforever.client.chat.ChatService;
import com.faforever.client.chat.MockChatService;
import com.faforever.client.chat.PircBotXChatService;
import com.faforever.client.chat.PircBotXFactory;
import com.faforever.client.chat.PircBotXFactoryImpl;
import com.faforever.client.connectivity.ConnectivityService;
import com.faforever.client.connectivity.ConnectivityServiceImpl;
import com.faforever.client.connectivity.TurnClient;
import com.faforever.client.connectivity.TurnClientImpl;
import com.faforever.client.events.EventService;
import com.faforever.client.events.EventServiceImpl;
import com.faforever.client.fa.ForgedAllianceService;
import com.faforever.client.fa.ForgedAllianceServiceImpl;
import com.faforever.client.game.GameService;
import com.faforever.client.game.GameServiceImpl;
import com.faforever.client.gravatar.GravatarService;
import com.faforever.client.gravatar.GravatarServiceImpl;
import com.faforever.client.gravatar.MockGravatarService;
import com.faforever.client.leaderboard.LeaderboardParser;
import com.faforever.client.leaderboard.LeaderboardService;
import com.faforever.client.leaderboard.LeaderboardServiceImpl;
import com.faforever.client.leaderboard.LegacyLeaderboardParser;
import com.faforever.client.leaderboard.MockLeaderboardService;
import com.faforever.client.legacy.LobbyServerAccessor;
import com.faforever.client.legacy.LobbyServerAccessorImpl;
import com.faforever.client.legacy.MockFafApiAccessor;
import com.faforever.client.legacy.MockLobbyServerAccessor;
import com.faforever.client.legacy.MockStatisticsServerAccessor;
import com.faforever.client.legacy.StatisticsServerAccessor;
import com.faforever.client.legacy.StatisticsServerAccessorImpl;
import com.faforever.client.legacy.UidService;
import com.faforever.client.legacy.UnixUidService;
import com.faforever.client.legacy.WindowsUidService;
import com.faforever.client.legacy.htmlparser.HtmlParser;
import com.faforever.client.legacy.map.LegacyMapVaultParser;
import com.faforever.client.legacy.map.MapVaultParser;
import com.faforever.client.lobby.LobbyService;
import com.faforever.client.lobby.LobbyServiceImpl;
import com.faforever.client.map.MapService;
import com.faforever.client.map.MapServiceImpl;
import com.faforever.client.mod.ModService;
import com.faforever.client.mod.ModServiceImpl;
import com.faforever.client.news.LegacyNewsService;
import com.faforever.client.news.NewsService;
import com.faforever.client.notification.NotificationService;
import com.faforever.client.notification.NotificationServiceImpl;
import com.faforever.client.patch.GameUpdateService;
import com.faforever.client.patch.GameUpdateServiceImpl;
import com.faforever.client.patch.GitWrapper;
import com.faforever.client.patch.JGitWrapper;
import com.faforever.client.patch.UpdateServerAccessor;
import com.faforever.client.patch.UpdateServerAccessorImpl;
import com.faforever.client.player.PlayerService;
import com.faforever.client.player.PlayerServiceImpl;
import com.faforever.client.preferences.PreferencesService;
import com.faforever.client.relay.LocalRelayServer;
import com.faforever.client.relay.LocalRelayServerImpl;
import com.faforever.client.replay.ReplayFileReader;
import com.faforever.client.replay.ReplayFileReaderImpl;
import com.faforever.client.replay.ReplayFileWriter;
import com.faforever.client.replay.ReplayFileWriterImpl;
import com.faforever.client.replay.ReplayServer;
import com.faforever.client.replay.ReplayServerAccessor;
import com.faforever.client.replay.ReplayServerAccessorImpl;
import com.faforever.client.replay.ReplayServerImpl;
import com.faforever.client.replay.ReplayService;
import com.faforever.client.replay.ReplayServiceImpl;
import com.faforever.client.reporting.ReportingService;
import com.faforever.client.reporting.ReportingServiceImpl;
import com.faforever.client.stats.StatisticsService;
import com.faforever.client.stats.StatisticsServiceImpl;
import com.faforever.client.task.TaskService;
import com.faforever.client.task.TaskServiceImpl;
import com.faforever.client.update.ClientUpdateService;
import com.faforever.client.update.ClientUpdateServiceImpl;
import com.faforever.client.update.MockClientUpdateService;
import com.faforever.client.update.MockGameUpdateService;
import com.faforever.client.uploader.ImageUploadService;
import com.faforever.client.uploader.imgur.ImgurImageUploadService;
import com.faforever.client.upnp.UpnpService;
import com.faforever.client.upnp.WeUpnpServiceImpl;
import com.faforever.client.user.UserService;
import com.faforever.client.user.UserServiceImpl;
import com.faforever.client.util.OperatingSystem;
import com.faforever.client.util.TimeService;
import com.faforever.client.util.TimeServiceImpl;
import com.google.api.client.util.Beta;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import javax.annotation.Resource;

@org.springframework.context.annotation.Configuration
@Import(BaseConfig.class)
public class ServiceConfig {

  @Resource
  Environment environment;

  @Bean
  HtmlParser htmlParser() {
    return new HtmlParser();
  }

  @Bean
  UserService userService() {
    return new UserServiceImpl();
  }

  @Bean
  LobbyServerAccessor lobbyServerAccessor() {
    if (environment.containsProperty("faf.testing")) {
      return new MockLobbyServerAccessor();
    }
    return new LobbyServerAccessorImpl();
  }

  @Bean
  FafApiAccessor fafApiAccessor() {
    if (environment.containsProperty("faf.testing")) {
      return new MockFafApiAccessor();
    }
    return new FafApiAccessorImpl();
  }

  @Bean
  StatisticsServerAccessor statisticsServerAccessor() {
    if (environment.containsProperty("faf.testing")) {
      return new MockStatisticsServerAccessor();
    }
    return new StatisticsServerAccessorImpl();
  }

  @Bean
  ReplayServerAccessor replayServerAccessor() {
    return new ReplayServerAccessorImpl();
  }

  @Bean
  UpdateServerAccessor updateServerAccessor() {
    return new UpdateServerAccessorImpl();
  }

  @Bean
  ChatService chatService() {
    if (environment.containsProperty("faf.testing")) {
      return new MockChatService();
    }
    return new PircBotXChatService();
  }

  @Bean
  MapVaultParser mapVaultParser() {
    return new LegacyMapVaultParser();
  }

  @Bean
  GameService gameService() {
    return new GameServiceImpl();
  }

  @Bean
  PlayerService playerService() {
    return new PlayerServiceImpl();
  }

  @Bean
  PreferencesService preferencesService() {
    return new PreferencesService();
  }

  @Bean
  ForgedAllianceService processService() {
    return new ForgedAllianceServiceImpl();
  }

  @Bean
  TaskScheduler taskScheduler() {
    return new ConcurrentTaskScheduler();
  }

  @Bean
  LocalRelayServer relayServer() {
    return new LocalRelayServerImpl();
  }

  @Bean
  ConnectivityService portCheckService() {
    return new ConnectivityServiceImpl();
  }

  @Bean
  LobbyService lobbyService() {
    return new LobbyServiceImpl();
  }

  @Bean
  ModService modService() {
    return new ModServiceImpl();
  }

  @Bean
  LeaderboardParser ladderParser() {
    return new LegacyLeaderboardParser();
  }

  @Bean
  ReplayServer replayServer() {
    return new ReplayServerImpl();
  }

  @Bean
  ReplayFileWriter replayFileWriter() {
    return new ReplayFileWriterImpl();
  }

  @Bean
  ReplayFileReader replayFileReader() {
    return new ReplayFileReaderImpl();
  }

  @Bean
  UpnpService upnpService() {
    return new WeUpnpServiceImpl();
  }

  @Bean
  LeaderboardService leaderboardService() {
    if (environment.containsProperty("faf.testing")) {
      return new MockLeaderboardService();
    }
    return new LeaderboardServiceImpl();
  }

  @Bean
  TurnClient turnClient() {
    return new TurnClientImpl();
  }

  @Bean
  GameUpdateService patchService() {
    if (environment.containsProperty("disable.update")) {
      return new MockGameUpdateService();
    }
    return new GameUpdateServiceImpl();
  }

  @Bean
  TaskService taskQueueService() {
    return new TaskServiceImpl();
  }

  @Bean
  NotificationService notificationService() {
    return new NotificationServiceImpl();
  }

  @Bean
  StatisticsService statisticsService() {
    return new StatisticsServiceImpl();
  }

  @Bean
  ReplayService replayService() {
    return new ReplayServiceImpl();
  }

  @Bean
  MapService mapService() {
    return new MapServiceImpl();
  }

  @Bean
  TimeService prettyTime() {
    return new TimeServiceImpl();
  }

  @Bean
  ReportingService reportingService() {
    return new ReportingServiceImpl();
  }

  @Bean
  ImageUploadService imageUploadService() {
    return new ImgurImageUploadService();
  }

  @Bean
  GitWrapper gitWrapper() {
    return new JGitWrapper();
  }

  @Bean
  PircBotXFactory pircBotXFactory() {
    return new PircBotXFactoryImpl();
  }

  @Bean
  NewsService newsService() {
    return new LegacyNewsService();
  }

  @Bean
  ClientUpdateService updateService() {
    if (environment.containsProperty("faf.testing")) {
      return new MockClientUpdateService();
    } else {
      return new ClientUpdateServiceImpl();
    }
  }

  @Bean
  UidService uidService() {
    switch (OperatingSystem.current()) {
      case WINDOWS:
        return new WindowsUidService();
      default:
        return new UnixUidService();
    }
  }

  @Bean
  AchievementService achievementService() {
    return new AchievementServiceImpl();
  }

  @Bean
  EventService eventService() {
    return new EventServiceImpl();
  }

  @Bean
  GravatarService gravatarService() {
    if (environment.containsProperty("faf.testing")) {
      return new MockGravatarService();
    }
    return new GravatarServiceImpl();
  }

  @Bean
  LocalHttpVerificationCodeReceiver verificationCodeReceiver() {
    return new LocalHttpVerificationCodeReceiver();
  }

  @Beta
  Directory directory() {
    return new RAMDirectory();
  }

  @Bean
  ThemeService themeService() {
    return new ThemeServiceImpl();
  }
}
