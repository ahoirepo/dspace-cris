/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 * 
 * http://www.dspace.org/license/
 * 
 * The document has moved 
 * <a href="https://svn.duraspace.org/dspace/licenses/LICENSE_HEADER">here</a>
 */
package it.cilea.hku.authority.model.dynamicfield.widget;

import it.cilea.osd.jdyna.editor.FilePropertyEditor;
import it.cilea.osd.jdyna.service.IPersistenceDynaService;
import it.cilea.osd.jdyna.util.ValidationMessage;
import it.cilea.osd.jdyna.value.FileValue;
import it.cilea.osd.jdyna.widget.WidgetFile;

import java.beans.PropertyEditor;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dspace.core.ConfigurationManager;

@Entity
@Table(name="model_grant_jdyna_widgetfile")
public class WidgetFileRG extends WidgetFile {

	@Override
	public FileValue getInstanceValore() {
		return new FileValue();
	}

	@Override
	public PropertyEditor getPropertyEditor(
			IPersistenceDynaService applicationService) {
		return new FilePropertyEditor<WidgetFileRG>(this);
	}

	@Override
	public Class<FileValue> getValoreClass() {
		return FileValue.class;
	}

	@Override
	public ValidationMessage valida(Object valore) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getBasePath() {
		return ConfigurationManager.getProperty("researchergrant.file.path");
	}
	
	@Override
	public String getServletPath() {
		return ConfigurationManager
				.getProperty("researchergrant.jdynafile.servlet.name");
	}
	
	@Override
	public String getCustomFolderByAuthority(String intAuth, String extAuth) {
		return intAuth + "/" + extAuth;
	}
}
