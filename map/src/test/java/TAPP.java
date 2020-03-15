import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.renfei.api.map.entity.MapDTO;
import net.renfei.map.service.MapImplService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author RenFei
 */
@Slf4j
@SpringBootTest(classes = TAPP.class)
@RunWith(SpringRunner.class)
public class TAPP {
    @Autowired
    private MapImplService mapImplService;

    @Test
    public void test() {
        List<MapDTO> mapDTOS = mapImplService.getMapsByDistance(39.87542694D, 116.47741423D, 10000, 10);
        log.info(JSON.toJSONString(mapDTOS));
    }
}
