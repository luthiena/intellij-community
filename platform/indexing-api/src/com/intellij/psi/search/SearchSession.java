/*
 * Copyright 2000-2012 JetBrains s.r.o.
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
package com.intellij.psi.search;

import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public final class SearchSession extends UserDataHolderBase {
  private final PsiElement[] myElements;

  public SearchSession() {
    this(PsiElement.EMPTY_ARRAY);
  }
  public SearchSession(PsiElement @NotNull ... elements) {
    myElements = elements;
  }

  @NotNull
  @Unmodifiable
  public List<VirtualFile> getTargetVirtualFiles() {
    return ContainerUtil.mapNotNull(myElements, e -> PsiUtilCore.getVirtualFile(e));
  }
}
