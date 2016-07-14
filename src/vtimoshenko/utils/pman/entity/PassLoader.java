package vtimoshenko.utils.pman.entity;

/**
 * Created by SimpleUser on 14.07.2016.
 */
public interface PassLoader {
    boolean savePass(CollectionPass coll);
    CollectionPass loadPass();
    CollectionPass loadPass(String file);
}
