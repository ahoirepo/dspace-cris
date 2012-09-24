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
package it.cilea.hku.authority.model.dynamicfield;

import it.cilea.osd.jdyna.model.Containable;
import it.cilea.osd.jdyna.web.Box;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CacheModeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;

@Entity
@Table(name = "model_jdyna_box")
@org.hibernate.annotations.NamedQueries({
		@org.hibernate.annotations.NamedQuery(name = "BoxRPAdditionalFieldStorage.findAll", query = "from BoxRPAdditionalFieldStorage order by priority asc"),
		@org.hibernate.annotations.NamedQuery(name = "BoxRPAdditionalFieldStorage.findContainableByHolder", query = "from Containable containable where containable in (select m from BoxRPAdditionalFieldStorage box join box.mask m where box.id = ?)", cacheable=true),
		@org.hibernate.annotations.NamedQuery(name = "BoxRPAdditionalFieldStorage.findHolderByContainable", query = "from BoxRPAdditionalFieldStorage box where :par0 in elements(box.mask)", cacheable=true),
		@org.hibernate.annotations.NamedQuery(name = "BoxRPAdditionalFieldStorage.uniqueBoxByShortName", query = "from BoxRPAdditionalFieldStorage box where shortName = ?")
})
public class BoxRPAdditionalFieldStorage extends Box<Containable> {
	
	@ManyToMany	
	@JoinTable(name = "model_jdyna_box2containable")	
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Containable> mask;

	public BoxRPAdditionalFieldStorage() {
		this.visibility = VisibilityTabConstant.ADMIN;
	}
	
	@Override
	public List<Containable> getMask() {
		if(this.mask==null) {
			this.mask = new LinkedList<Containable>();
		}		
		return mask;
	}

	@Override
	public void setMask(List<Containable> mask) {
		if(mask!=null) {
			Collections.sort(mask);
		}
		this.mask = mask;
	}

	
}
