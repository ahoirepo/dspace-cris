package it.cilea.hku.authority.model.dynamicfield;

import it.cilea.osd.jdyna.model.ADecoratorTypeDefinition;
import it.cilea.osd.jdyna.model.IContainable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@NamedQueries( {
    @NamedQuery(name = "DecoratorRPTypeNested.findAll", query = "from DecoratorRPTypeNested order by id"),
    @NamedQuery(name = "DecoratorRPTypeNested.uniqueContainableByDecorable", query = "from DecoratorRPTypeNested where real.id = ?"),
    @NamedQuery(name = "DecoratorRPTypeNested.uniqueContainableByShortName", query = "from DecoratorRPTypeNested where real.shortName = ?")
    
})
@DiscriminatorValue(value="typerpnestedobject")
public class DecoratorRPTypeNested extends
    ADecoratorTypeDefinition<RPTypeNestedObject, RPNestedPropertiesDefinition>
{

    @OneToOne(optional=true)
    @JoinColumn(name="typerpnestedobject_fk")
    @Cascade(value = {CascadeType.ALL,CascadeType.DELETE_ORPHAN})
    private RPTypeNestedObject real;

    @Override
    public String getShortName()
    {        
        return this.real.getShortName();
    }

    @Override
    public Integer getAccessLevel()
    {
        return this.real.getAccessLevel();
    }

    @Override
    public String getLabel()
    {
        return this.real.getDescrizione();
    }

    @Override
    public boolean getRepeatable()
    {
        return this.real.isRepeatable();
    }

    @Override
    public int getPriority()
    {
        return this.real.getPriority();
    }

    @Override
    public int compareTo(IContainable o) {
        RPTypeNestedObject oo = null;
        if(o instanceof DecoratorRPTypeNested) {
            oo = (RPTypeNestedObject)o.getObject();
            return this.real.compareTo(oo);
        }
        return 0;
    }
    
    @Override
    public void setReal(RPTypeNestedObject object)
    {
       this.real = object;        
    }

    @Override
    public RPTypeNestedObject getObject()
    {
        return real;
    }

    
}
