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
package it.cilea.hku.authority.model;

import it.cilea.hku.authority.model.dynamicfield.ProjectPropertiesDefinition;
import it.cilea.hku.authority.model.dynamicfield.ProjectProperty;
import it.cilea.hku.authority.model.dynamicfield.ProjectAdditionalFieldStorage;
import it.cilea.hku.authority.model.dynamicfield.RPAdditionalFieldStorage;
import it.cilea.hku.authority.model.export.ExportConstants;
import it.cilea.osd.common.core.HasTimeStampInfo;
import it.cilea.osd.common.core.TimeStampInfo;
import it.cilea.osd.common.model.Identifiable;
import it.cilea.osd.jdyna.model.AnagraficaSupport;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "cris_project")
@NamedQueries({
        @NamedQuery(name = "Project.findAll", query = "from Project order by id"),
        @NamedQuery(name = "Project.count", query = "select count(*) from Project"),
        @NamedQuery(name = "Project.paginate.id.asc", query = "from Project order by id asc"),
        @NamedQuery(name = "Project.paginate.id.desc", query = "from Project order by id desc"),
        @NamedQuery(name = "Project.uniqueRGByCode", query = "from Project where code = ? order by id desc") })
public class Project
        implements
        Identifiable,
        UUIDSupport,
        HasTimeStampInfo,
        Cloneable,
        IExportableDynamicObject<ProjectPropertiesDefinition, ProjectProperty, ProjectAdditionalFieldStorage>,
        AnagraficaSupport<ProjectProperty, ProjectPropertiesDefinition>
{

    @Transient
    /**
     * Constant for resource type assigned to the Researcher Grants
     */
    public static final int GRANT_TYPE_ID = 10;

    /** DB Primary key */
    @Id
    @GeneratedValue(generator = "CRIS_PROJECT_SEQ")
    @SequenceGenerator(name = "CRIS_PROJECT_SEQ", sequenceName = "CRIS_PROJECT_SEQ")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uuid;

    /** timestamp info for creation and last modify */
    @Embedded
    private TimeStampInfo timeStampInfo;

    /**
     * Map of additional custom data
     */
    @Embedded
    private ProjectAdditionalFieldStorage dynamicField;

    /** True if grant is active */
    private Boolean status;

    /** Grant code */
    private String code;

    public Project()
    {
        this.status = true;
        this.dynamicField = new ProjectAdditionalFieldStorage();
    }

    public Investigator getInvestigator()
    {
        // TODO return a JDynA pointer
        return null;
    }

    public List<Investigator> getCoInvestigators()
    {
        // TODO return a JDynA pointer
        return null;
    }

    /**
     * Getter method.
     * 
     * @return the timestamp of creation and last modify of this Project
     */
    public TimeStampInfo getTimeStampInfo()
    {
        if (timeStampInfo == null)
        {
            timeStampInfo = new TimeStampInfo();
        }
        return timeStampInfo;
    }

    /**
     * Getter method.
     * 
     * @return the status
     */
    public Boolean getStatus()
    {
        return status;
    }

    /**
     * Setter method.
     * 
     * @param id
     *            the status of this Project
     */
    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    public void setCode(String rgCode)
    {
        this.code = rgCode;
    }

    public String getCode()
    {
        return code;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    public String getTitle()
    {
        String result = "";
        for (ProjectProperty title : this.getDynamicField()
                .getAnagrafica4view().get("projecttitle"))
        {
            result += title.getValue().getObject();
            result += " ";
        }
        return result;
    }

    public String getYear()
    {
        String result = "";
        for (ProjectProperty year : this.getDynamicField().getAnagrafica4view()
                .get("fundingyear"))
        {
            result += year.getValue().getObject();
            result += " ";
        }
        return result;
    }

    public String getInvestigatorToDisplay()
    {
        Investigator inv = getInvestigator();
        if (inv != null)
        {
            if (inv.getIntInvestigator() != null)
            {
                return inv.getIntInvestigator().getFullName();
            }
            else
            {
                return inv.getExtInvestigator();
            }
        }
        return "";
    }

    @Override
    public String getNamePublicIDAttribute()
    {
        return ExportConstants.NAME_PUBLICID_ATTRIBUTE;
    }

    @Override
    public String getValuePublicIDAttribute()
    {
        return "" + this.getId();
    }

    @Override
    public String getNameIDAttribute()
    {
        return ExportConstants.NAME_ID_ATTRIBUTE;
    }

    @Override
    public String getValueIDAttribute()
    {
        if (this.getUuid() == null)
        {
            return "";
        }
        return "" + this.getUuid().toString();
    }

    @Override
    public String getNameBusinessIDAttribute()
    {
        return ExportConstants.NAME_BUSINESSID_ATTRIBUTE;
    }

    @Override
    public String getValueBusinessIDAttribute()
    {
        return this.getCode();
    }

    @Override
    public String getNameTypeIDAttribute()
    {
        return ExportConstants.NAME_TYPE_ATTRIBUTE;
    }

    @Override
    public String getValueTypeIDAttribute()
    {
        return "" + GRANT_TYPE_ID;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getUuid()
    {
        return uuid;
    }

    @Override
    public String getNameSingleRowElement()
    {
        return ExportConstants.ELEMENT_SINGLEROW;
    }

    @Override
    public ProjectAdditionalFieldStorage getDynamicField()
    {
        if (this.dynamicField == null)
        {
            this.dynamicField = new ProjectAdditionalFieldStorage();
        }
        return dynamicField;
    }

    public void setDynamicField(ProjectAdditionalFieldStorage dynamicField)
    {
        this.dynamicField = dynamicField;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    @Override
    public String getIdentifyingValue()
    {
        return this.dynamicField.getIdentifyingValue();
    }

    @Override
    public String getDisplayValue()
    {
        return this.dynamicField.getDisplayValue();
    }

    @Override
    public List<ProjectProperty> getAnagrafica()
    {
        return this.dynamicField.getAnagrafica();
    }

    @Override
    public Map<String, List<ProjectProperty>> getAnagrafica4view()
    {
        return this.dynamicField.getAnagrafica4view();
    }

    @Override
    public void setAnagrafica(List<ProjectProperty> anagrafica)
    {
        this.dynamicField.setAnagrafica(anagrafica);
    }

    @Override
    public ProjectProperty createProprieta(
            ProjectPropertiesDefinition tipologiaProprieta)
            throws IllegalArgumentException
    {
        return this.dynamicField.createProprieta(tipologiaProprieta);
    }

    @Override
    public ProjectProperty createProprieta(
            ProjectPropertiesDefinition tipologiaProprieta, Integer posizione)
            throws IllegalArgumentException
    {
        return this.dynamicField.createProprieta(tipologiaProprieta, posizione);
    }

    @Override
    public boolean removeProprieta(ProjectProperty proprieta)
    {
        return this.dynamicField.removeProprieta(proprieta);
    }

    @Override
    public List<ProjectProperty> getProprietaDellaTipologia(
            ProjectPropertiesDefinition tipologiaProprieta)
    {
        return this.dynamicField.getProprietaDellaTipologia(tipologiaProprieta);
    }

    @Override
    public Class<ProjectProperty> getClassProperty()
    {
        return this.dynamicField.getClassProperty();
    }

    @Override
    public Class<ProjectPropertiesDefinition> getClassPropertiesDefinition()
    {
        return this.dynamicField.getClassPropertiesDefinition();
    }

    @Override
    public void inizializza()
    {
        this.dynamicField.inizializza();
    }

    @Override
    public void invalidateAnagraficaCache()
    {
        this.dynamicField.invalidateAnagraficaCache();
    }

    @Override
    public void pulisciAnagrafica()
    {
        this.dynamicField.pulisciAnagrafica();
    }

    public void setInvestigator(Investigator inv)
    {
        // TODO Auto-generated method stub

    }

    public void setCoInvestigators(List<Investigator> coinvestigators)
    {
        // TODO Auto-generated method stub

    }

}