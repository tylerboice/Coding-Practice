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
        String testPath = "(1, 4)\n(1, 3)\n(1, 2)\n(2, 2)\n(3, 2)\n(3, 1)\n(3, 0)";
        tester.initializeMaze("InputMaze.txt");
        Assert.assertEquals(testPath, tester.pathString());
    }

    @Test
    public void testPath() {
        ColorMaze tester = new ColorMaze();
        tester.initializeMaze("InputMaze.txt");
        tester.pathString();
    }

    @Test
    public void testChallengeLegend() {
        ColorMaze tester = new ColorMaze();
        String testLegend = "ROYPO";
        tester.initializeMaze("InputChallenge.txt");
        Assert.assertEquals(testLegend, tester.legendString());
    }

    @Test
    public void testChallengeMaze() {
        ColorMaze tester = new ColorMaze();
        String testMaze = "RRBRRRBPYGPBBBGPBPPR\nBGYPRPYYORYPPYYRRRPP\nBPGROPYGRYYGPORYPBOO\nRBBORPYOOYRPBRGRBGPG\n" +
                "RPYGGGPYPYOGBORYPBYO\nORBGBYBPGRPYROGYGYRP\nBGOOOGBBROYYYYPBYYGG\nPPGBOPYGBROGBGROYRBR\n" +
                "YYPPRBYBPOOGPYRPPYRY\nPOOBBBGOYGOPBGYRRYRB\nPPYRBOORORYBGBGOOPBY\nBBRGYGPYGPRRPYGOOYRR\n" +
                "OGRYBPYOPBRYBGPGOOBP\nRYGPGGORYOOGRGPPYPBG\nPYPROOROYRPORYPYBBYR\nOYPGRPRGPOBBRBOBYYBP\n" +
                "BYYPOYOYORBRGGYGRGYG\nYBYYGBRROBOPPOBORRRP\nPOOOPYGGYPOGPOBGPRPB\nRBBRRRRBBBYOBGPGGOOY";
        tester.initializeMaze("InputChallenge.txt");
        Assert.assertEquals(testMaze, tester.mazeString());
    }

   @Test
   public void testChallengePath() {
       ColorMaze tester = new ColorMaze();
       tester.initializeMaze("InputChallenge.txt");
       System.out.println(tester.pathString());
   }
}
