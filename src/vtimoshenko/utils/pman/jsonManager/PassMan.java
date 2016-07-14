package vtimoshenko.utils.pman.jsonManager;

import vtimoshenko.lib.json.ClusterException;
import vtimoshenko.lib.json.ClusterOne;
import vtimoshenko.utils.pman.entity.CollectionPass;
import vtimoshenko.utils.pman.entity.PassLoader;

/**
 * Created by SimpleUser on 14.07.2016.
 */
public class PassMan implements PassLoader {
    private ClusterOne data;

    public PassMan(){
        data = new ClusterOne();
        try {
            data.createArray("", "sys");
            data.createArray("", "lab");
        } catch (ClusterException e) {
            e.printStackTrace();
        }
    }
    public PassMan(String file){

    }

    @Override
    public boolean savePass(CollectionPass coll) {
        return false;
    }

    @Override
    public CollectionPass loadPass() {
        return null;
    }

    @Override
    public CollectionPass loadPass(String file) {
        return null;
    }
}
