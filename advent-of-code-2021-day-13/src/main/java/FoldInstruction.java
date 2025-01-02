public record FoldInstruction(FoldAxis foldAxis, int foldLine) {

    enum FoldAxis {
        X, Y
    }

}
