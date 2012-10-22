package it.cilea.hku.authority.model.dynamicfield;

import it.cilea.osd.jdyna.model.ATypeNestedObject;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
*
* @author pascarelli
*
*/
@Entity
@Table(name = "cris_ou_nestedobject_typo")
@NamedQueries ({
    @NamedQuery(name="OUTypeNestedObject.findAll", query = "from OUTypeNestedObject order by id" ),
    @NamedQuery(name="OUTypeNestedObject.uniqueByNome", query = "from OUTypeNestedObject where shortName = ?" )              
})
public class OUTypeNestedObject extends ATypeNestedObject<OUNestedPropertiesDefinition>
{
    @ManyToMany
    @JoinTable(name = "cris_ou_nestedobject_typo2mask")
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<OUNestedPropertiesDefinition> mask;

    @Override
    public List<OUNestedPropertiesDefinition> getMask()
    {
        return mask;
    }

    public void setMask(List<OUNestedPropertiesDefinition> mask) {
        this.mask = mask;
    }

    @Override
    public Class getDecoratorClass()
    {
        return DecoratorOUTypeNested.class;
    }

}