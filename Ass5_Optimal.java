import java.util.*;

public class Ass5{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Input for frames and pages
        System.out.print("Enter the number of frames: ");
        int numFrames = sc.nextInt();
        int[] frames = new int[numFrames];
        Arrays.fill(frames, -1);

        System.out.print("Enter the number of pages: ");
        int numPages = sc.nextInt();
        int[] pages = new int[numPages];
        
        System.out.println("Enter the page numbers: ");
        for (int i = 0; i < numPages; i++) pages[i] = sc.nextInt();

        int faults = 0, hits = 0;

        for (int i = 0; i < numPages; i++) {
            int currentPage = pages[i];
            boolean hit = false;

            // Check for page hit
            for (int frame : frames) if (frame == currentPage) { hit = true; hits++; break; }

            // Page fault handling
            if (!hit) {
                int frameToReplace = -1, farthest = i;

                // Find an empty frame or choose the frame with farthest next use
                for (int j = 0; j < numFrames; j++) {
                    if (frames[j] == -1) { frameToReplace = j; break; }
                    int nextUse = numPages;
                    for (int k = i + 1; k < numPages; k++) if (pages[k] == frames[j]) { nextUse = k; break; }
                    if (nextUse > farthest) { farthest = nextUse; frameToReplace = j; }
                }
                
                frames[frameToReplace] = currentPage; // Replace page
                faults++;
            }
        }

        System.out.println("\nTotal Page Faults: " + faults + "\nTotal Page Hits: " + hits);
        sc.close();
    }
}
