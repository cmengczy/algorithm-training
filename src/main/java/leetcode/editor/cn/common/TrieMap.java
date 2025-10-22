package leetcode.editor.cn.common;

import java.util.LinkedList;
import java.util.List;

public class TrieMap<V> {
    int r = 256;
    private int size;
    private TrieNode<V> root;

    public TrieMap() {
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        for (int i = 0; i < key.length(); i++) {
            if (node == null) {
                return null;
            }
            char c = key.charAt(i);
            node = node.children[c];
        }
        return node;
    }

    public V get(String key) {
        TrieNode<V> node = getNode(root, key);
        if (node == null || node.value == null) {
            return null;
        }
        return node.value;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public boolean hashKeyWithPrefix(String key) {
        return getNode(root, key) != null;
    }

    public String shortestPrefixOf(String query) {
        TrieNode<V> p = root;
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                return "";
            }
            if (p.value != null) {
                return query.substring(0, i);
            }
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.value != null) {
            return query;
        }
        return "";
    }

    public String longestPrefixOf(String query) {
        TrieNode<V> p = root;
        int maxLen = 0;
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                break;
            }
            if (p.value != null) {
                maxLen = i;
            }
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.value != null) {
            return query;
        }
        return query.substring(0, maxLen);
    }

    public List<String> keysWithPrefix(String prefix) {
        LinkedList<String> res = new LinkedList<>();
        TrieNode<V> p = getNode(root, prefix);
        if (p == null) {
            return res;
        }
        traverse(p, new StringBuilder(prefix), res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, List<String> res) {
        if (node == null) {
            return;
        }
        if (node.value != null) {
            res.add(path.toString());
        }
        for (char c = 0; c < 256; c++) {
            path.append(c);
            traverse(node.children[c], path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public List<String> keysWithPattern(String pattern) {
        LinkedList<String> res = new LinkedList<>();
        traverse(root, new StringBuilder(""), pattern, 0, res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int index, List<String> res) {
        if (node == null) {
            return;
        }
        if (pattern.length() == index) {
            if (node.value != null) {
                res.add(path.toString());
            }
            return;
        }
        char pc = path.charAt(index);
        if (pc == '.') {
            for (char c = 0; c < 256; c++) {
                path.append(c);
                traverse(node.children[c], path, pattern, index + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            path.append(pc);
            traverse(node.children[pc], path, pattern, index + 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public boolean hasKeyWithPattern(String pattern) {
        return hasKeyWithPattern(root, pattern, 0);
    }

    private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int index) {
        if (node == null) {
            return false;
        }
        if (pattern.length() == index) {
            if (node.value != null) {
                return true;
            }
        }
        char pc = pattern.charAt(index);
        if (pc == '.') {
            for (char i = 0; i < 256; i++) {
                if (hasKeyWithPattern(node.children[i], pattern, index + 1)) {
                    return true;
                }
            }
        } else {
            return hasKeyWithPattern(node.children[pc], pattern, index + 1);
        }
        return false;
    }

    public void put(String key, V value) {
        if (!contains(key)) {
            size++;
        }
        put(root, key, 0, value);
    }

    private TrieNode<V> put(TrieNode<V> node, String key, int index, V value) {
        if (node == null) {
            node = new TrieNode<>();
        }
        if (key.length() == index) {
            node.value = value;
        }
        char c = key.charAt(index);
        node.children[c] = put(node.children[c], key, index + 1, value);
        return node;
    }

    public void remove(String key) {
        if (!contains(key)) {
            return;
        }
        // 递归修改数据结构要接收函数的返回值
        root = remove(root, key, 0);
        size--;
    }

    private TrieNode<V> remove(TrieNode<V> node, String key, int index) {
        if (node == null) {
            return null;
        }
        if (index == key.length()) {
            // 找到了 key 对应的 TrieNode，删除 val
            node.value = null;
        } else {
            char c = key.charAt(index);
            // 递归去子树进行删除
            node.children[c] = remove(node.children[c], key, index + 1);
        }
        // 后序位置，递归路径上的节点可能需要被清理
        if (node.value != null) {
            // 如果该 TireNode 存储着 val，不需要被清理
            return node;
        }
        // 检查该 TrieNode 是否还有后缀
        for (int c = 0; c < 256; c++) {
            if (node.children[c] != null) {
                // 只要存在一个子节点（后缀树枝），就不需要被清理
                return node;
            }
        }
        // 既没有存储 val，也没有后缀树枝，则该节点需要被清理
        return null;
    }

    static class TrieNode<V> {
        V value;
        TrieNode[] children = new TrieNode[256];

        public TrieNode() {
            this.children = new TrieNode[256];
        }
    }
}
