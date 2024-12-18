/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.openapi.externalSystem.service.remote;

import com.intellij.openapi.externalSystem.model.ExternalSystemException;
import com.intellij.openapi.externalSystem.model.settings.ExternalSystemExecutionSettings;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskId;
import com.intellij.openapi.externalSystem.task.ExternalSystemTaskManager;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public class RemoteExternalSystemTaskManagerImpl<S extends ExternalSystemExecutionSettings>
  extends AbstractRemoteExternalSystemService<S> implements RemoteExternalSystemTaskManager<S> {

  @NotNull private final ExternalSystemTaskManager<S> myDelegate;

  public RemoteExternalSystemTaskManagerImpl(@NotNull ExternalSystemTaskManager<S> delegate) {
    myDelegate = delegate;
  }

  @Override
  public void executeTasks(
    @NotNull String projectPath,
    @NotNull ExternalSystemTaskId id,
    @NotNull S settings
  ) throws ExternalSystemException {
    execute(id, () -> {
      myDelegate.executeTasks(projectPath, id, settings, getNotificationListener());
      return null;
    });
  }

  @Override
  public boolean cancelTask(
    @NotNull ExternalSystemTaskId id
  ) throws ExternalSystemException {
    return myDelegate.cancelTask(id, getNotificationListener());
  }
}
