/*
 * Copyright (c) 2007-2009, Osmorc Development Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright notice, this list
 *       of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice, this
 *       list of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *     * Neither the name of 'Osmorc Development Team' nor the names of its contributors may be
 *       used to endorse or promote products derived from this software without specific
 *       prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.osmorc.settings;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;
import org.osmorc.manifest.ManifestFileTypeFactory;

/**
 * Editor component for editing a Manifest with syntax highlighting and code completion. This is used in various settings dialogs.
 *
 * @author <a href="janthomae@janthomae.de">Jan Thom&auml;</a>
 * @version $Id:$
 */
public class ManifestEditor extends EditorTextField implements Disposable {
  private final ManifestEditor.MyDocumentAdapter listener = new MyDocumentAdapter();


  public ManifestEditor(Project project, String text) {
    super("", project, ManifestFileTypeFactory.MANIFEST);
    addDocumentListener(listener);
    setText(text);
  }

  public void setText(String text) {
    if (text == null) {
      text = "";
    }
    super.setText(text);
  }

  protected EditorEx createEditor() {
    EditorEx editor = super.createEditor();
    editor.setVerticalScrollbarVisible(true);
    editor.setHorizontalScrollbarVisible(true);
    return editor;
  }

  @Override
  protected boolean isOneLineMode() {
    return false;
  }

  public void dispose() {
    removeDocumentListener(listener);
  }


  private class MyDocumentAdapter extends DocumentAdapter {
    @Override
    public void documentChanged(DocumentEvent e) {
      firePropertyChange("text", null, null);
    }
  }
}
