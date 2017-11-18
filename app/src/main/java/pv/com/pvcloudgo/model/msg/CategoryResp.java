package pv.com.pvcloudgo.model.msg;

import java.util.List;

import pv.com.pvcloudgo.model.base.BaseRespMsg;
import pv.com.pvcloudgo.model.bean.CRoot1;
import pv.com.pvcloudgo.model.bean.Category;

/**
 * Created by stefan on 17/1/9.
 */

public class CategoryResp extends BaseRespMsg {

    public Result results;

    public Result getResults() {
        return results;
    }

    public class Result {
        List<Category> ptTypeList;

        CRoot1 firstRoot;

        public List<Category> getPtTypeList() {
            return ptTypeList;
        }

        public CRoot1 getFirstRoot() {
            return firstRoot;
        }
    }
}
