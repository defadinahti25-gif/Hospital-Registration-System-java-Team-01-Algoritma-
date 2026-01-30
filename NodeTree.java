import java.util.ArrayList;
import java.util.List;

public class NodeTree {
    String namaBagian;
    List<NodeTree> subBagian; 

    public NodeTree(String namaBagian) {
        this.namaBagian = namaBagian;
        this.subBagian = new ArrayList<>();
    }

    // Menambahkan anak (child)
    public void tambahSubBagian(NodeTree node) {
        subBagian.add(node);
    }

    // Menampilkan Tree secara Rekursif
    public void printTree(String indent) {
        System.out.println(indent + "└── " + namaBagian);
        for (NodeTree node : subBagian) {
            node.printTree(indent + "    ");
        }
    }
}