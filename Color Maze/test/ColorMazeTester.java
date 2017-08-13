//MazeColorTester.java by Tyler Boice 8/12/17

import org.junit.*;


public class ColorMazeTester {
    @Test
    public void testCorrectLegend() {
        ColorMaze tester = new ColorMaze();
        String testLegend = "OG";
        tester.initializeMaze("InputMaze.txt");
        Assert.assertEquals(testLegend, tester.legendString());
    }

    @Test
    public void testCorrectMaze() {
        ColorMaze tester = new ColorMaze();
        String testMaze = "BOROY\nORBGR\nBOGOY\nYGBYG\nRORBR";
        tester.initializeMaze("InputMaze.txt");
        Assert.assertEquals(testMaze, tester.mazeString());
    }

    @Test
    public void testCorrectPath() {
        ColorMaze tester = new ColorMaze();
        String testPath = "(1,4)\n(1,3)\n(1,2)\n(2,2)\n(3,2)\n(3,1)\n(3,0)";
        tester.initializeMaze("InputMaze.txt");
        Assert.assertEquals(testPath, tester.printPath());
    }
}
