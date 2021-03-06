/**
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
package org.activiti.designer.property.ui;

import java.util.List;

import org.activiti.bpmn.model.FormValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;


public class FormValueEditor extends TableFieldEditor {
  
  protected Composite parent;
  protected boolean enableTableChanges = true;
	
  public FormValueEditor(String key, Composite parent) {
    super(key, "", new String[] {"Id", "Name"},
        new int[] {200, 200}, parent);
    this.parent = parent;
  }

  public void initialize(List<FormValue> formValues) {
    removeTableItems();
    if (formValues != null) {
      for (FormValue formValue : formValues) {
        addTableItem(formValue);
      }
    }
  }
  
  @Override
  protected boolean isTableChangeEnabled() {
    return true;
  }

  @Override
  protected String createList(String[][] items) {
    return null;
  }

  @Override
  protected String[][] parseString(String string) {
    return null;
  }
  
  protected void addTableItem(FormValue formValue) {
    
    if (table != null) {
    	TableItem tableItem = new TableItem(table, SWT.NONE);
      tableItem.setText(0, formValue.getId());
      tableItem.setText(1, formValue.getName());
    }
  }

  @Override
  protected String[] getNewInputObject() {
  	FormValueDialog dialog = new FormValueDialog(parent.getShell(), getItems());
    dialog.open();
    if(dialog.id != null && dialog.id.length() > 0) {
      return new String[] { dialog.id, dialog.name};
    } else {
      return null;
    }
  }
  
  @Override
  protected String[] getChangedInputObject(TableItem item) {
  	FormValueDialog dialog = new FormValueDialog(parent.getShell(), getItems(), 
            item.getText(0), item.getText(1));
    dialog.open();
    if(dialog.id != null && dialog.id.length() > 0) {      
      return new String[] { dialog.id, dialog.name};
    } else {
      return null;
    }
  }
  
  @Override
  protected void removedItem(int index) {
	  // TODO Auto-generated method stub 
  }
}
