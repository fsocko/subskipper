package fps.subskipper.core.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoreStringUtilsTest {

    @Test
    public void stripSpaces() {

        assertEquals(expectedShipsNoSpaces(), CoreStringUtils.stripSpaces(expectedShips() ));


    }

    private String expectedShips(){

        String expectedShips = "Ship{id=1, nation='none', name='Small Old Split Freighter', type=16, typeName='Elite Patrol Craft', imagePath='\"D:\\002-git\\SubSkipper\\shipDataReader\\src\\main\\resources\\SCAF for TMO_2\\Data\\Sea\\AuxGunboat\\AuxGunboat_sil.dds\"', maxSpeed=17.0, length=80.5, width=13.0, mast=17.9, draft=3.7, displacement=1850.0, refAspect=4.4972067039106145}\r\n"
                + "\r\n"
                + "Ship{id=1, nation='none', name=' Auxilary Subchaser', type=1, typeName='Corvette', imagePath='\"D:\\002-git\\SubSkipper\\shipDataReader\\src\\main\\resources\\SCAF for TMO_2\\Data\\Sea\\AuxSubchaser\\AuxSubchaser_sil.dds\"', maxSpeed=17.0, length=63.0, width=12.0, mast=11.4, draft=4.2, displacement=680.0, refAspect=5.526315789473684}\r\n"
                + "\r\n"
                + "Ship{id=1, nation='none', name='Type C Escort', type=3, typeName='Destroyer Escort', imagePath='\"D:\\002-git\\SubSkipper\\shipDataReader\\src\\main\\resources\\SCAF for TMO_2\\Data\\Sea\\COKaibokan\\COKaibokan_sil.dds\"', maxSpeed=19.0, length=89.5, width=11.1, mast=27.7, draft=3.4, displacement=900.0, refAspect=3.2310469314079424}\r\n"
                + "\r\n"
                + "Ship{id=1, nation='none', name='Type D Escort', type=1, typeName='Corvette', imagePath='\"D:\\002-git\\SubSkipper\\shipDataReader\\src\\main\\resources\\SCAF for TMO_2\\Data\\Sea\\COKaibokan2\\COKaibokan2_sil.dds\"', maxSpeed=19.0, length=89.5, width=11.1, mast=27.7, draft=3.4, displacement=900.0, refAspect=3.2310469314079424}\r\n"
                + "\r\n";
        return expectedShips;
    }

    private String expectedShipsNoSpaces() {

        String expectedShips = "Ship{id=1, nation='none', name='Small Old Split Freighter', type=16, typeName='Elite Patrol Craft', imagePath='\"D:\\002-git\\SubSkipper\\shipDataReader\\src\\main\\resources\\SCAF for TMO_2\\Data\\Sea\\AuxGunboat\\AuxGunboat_sil.dds\"', maxSpeed=17.0, length=80.5, width=13.0, mast=17.9, draft=3.7, displacement=1850.0, refAspect=4.4972067039106145}"
                + "Ship{id=1, nation='none', name=' Auxilary Subchaser', type=1, typeName='Corvette', imagePath='\"D:\\002-git\\SubSkipper\\shipDataReader\\src\\main\\resources\\SCAF for TMO_2\\Data\\Sea\\AuxSubchaser\\AuxSubchaser_sil.dds\"', maxSpeed=17.0, length=63.0, width=12.0, mast=11.4, draft=4.2, displacement=680.0, refAspect=5.526315789473684}"
                + "Ship{id=1, nation='none', name='Type C Escort', type=3, typeName='Destroyer Escort', imagePath='\"D:\\002-git\\SubSkipper\\shipDataReader\\src\\main\\resources\\SCAF for TMO_2\\Data\\Sea\\COKaibokan\\COKaibokan_sil.dds\"', maxSpeed=19.0, length=89.5, width=11.1, mast=27.7, draft=3.4, displacement=900.0, refAspect=3.2310469314079424}"
                + "Ship{id=1, nation='none', name='Type D Escort', type=1, typeName='Corvette', imagePath='\"D:\\002-git\\SubSkipper\\shipDataReader\\src\\main\\resources\\SCAF for TMO_2\\Data\\Sea\\COKaibokan2\\COKaibokan2_sil.dds\"', maxSpeed=19.0, length=89.5, width=11.1, mast=27.7, draft=3.4, displacement=900.0, refAspect=3.2310469314079424}";
        return expectedShips;
    }
}