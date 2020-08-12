package yyl.leetcode.bean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node {

	public int val;
	public List<Node> neighbors;

	public Node() {
		this(0);
	}

	public Node(int val) {
		this(val, new ArrayList<>());
	}

	public Node(int val, ArrayList<Node> neighbors) {
		this.val = val;
		this.neighbors = neighbors;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		return toString().equals(toString((Node) object));
	}

	public String toString() {
		return toString(this);
	}

	/**
	 * 创建图
	 * @param edges 图边数据
	 * @return 图第一个节点
	 */
	public static Node createGraph(String edges) {
		StringBuilder trim = new StringBuilder(edges.length());
		for (char c : edges.toCharArray()) {
			if (c != ' ') {
				trim.append(c);
			}
		}
		if ("[]".equals(edges)) {
			return null;
		}
		Map<Integer, Node> nodes = new HashMap<>();
		Matcher matcher = Pattern.compile("(?<=\\[)[\\d,]+?(?=\\])").matcher(edges);
		for (int index = 1; matcher.find(); index++) {
			Node node = nodes.get(index);
			if (node == null) {
				nodes.put(index, node = new Node(index));
			}
			String group = matcher.group();
			if (group.isEmpty()) {
				continue;
			}
			for (String part : group.split(",")) {
				int atom = Integer.parseInt(part);
				Node neighbor = nodes.get(atom);
				if (neighbor == null) {
					nodes.put(atom, neighbor = new Node(atom));
				}
				node.neighbors.add(neighbor);
			}
		}
		return nodes.get(1);
	}

	/**
	 * 返回图的字符串表示形式
	 * @param node 图第一个节点
	 * @return 字符串表示形式(图的边)
	 */
	public static String toString(Node node) {
		if (node == null) {
			return "[]";
		}
		Map<Integer, Node> map = new TreeMap<>();
		Deque<Node> queue = new ArrayDeque<>();
		queue.add(node);
		int max = 0;
		while (!queue.isEmpty()) {
			Node n = queue.poll();
			map.put(n.val, n);
			max = Math.max(max, n.val);
			for (Node a : n.neighbors) {
				if (!map.containsKey(a.val)) {
					queue.add(a);
				}
			}
		}
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (int i = 1; i <= max; i++) {
			Node n = map.get(i);
			if (n == null || n.neighbors.isEmpty()) {
				builder.append("[]");
			} else {
				builder.append("[");
				for (Node a : n.neighbors) {
					builder.append(a.val).append(',');
				}
				builder.setCharAt(builder.length() - 1, ']');
			}
			builder.append(",");
		}
		builder.setCharAt(builder.length() - 1, ']');
		return builder.toString();
	}
}
