package Objects.Factory;

/**
 * @author Eleena Rath
 * 
 * The abstract RecordFactory serves to implement the factory design pattern since this program is expected to have many 
 * kinds of records. Within each subclasses implementation of the createRecord() method, the concrete factory will take
 * the user through the process of creating the specific kind of record that factory produces.
 */
public abstract class RecordFactory {
    
    public abstract Record createRecord();
}
