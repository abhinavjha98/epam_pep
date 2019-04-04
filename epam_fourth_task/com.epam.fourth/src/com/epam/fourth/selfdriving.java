package com.epam.fourth;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;

public class selfdriving {
    InputStream is;
    PrintWriter out;
    String INPUT = "";

    void solve() {
        int n = ni();
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ni() - 1;
            to[i] = ni() - 1;
        }
        int[][] graph = makeGraph(n, from, to);
        int[][] data = makeData(graph, 0);
        int[] parents = data[0];
        int[] orders = data[1];
        int[] iord = new int[n];
        for (int i = 0; i < n; i++) {
            iord[orders[i]] = i;
        }
        Node[] nodes = new Node[n];
        int[] nodesMap = new int[n];
        Arrays.fill(nodesMap, -1);
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int cur = orders[i];
            int curind = -search(nodes[cur], cur) - 1; // #less

            int low = -1;
            int high = curind;
            while (high - low > 1) {
                int h = high + low >>> 1;
                if (cur - get(nodes[cur], h).v == curind - h) {
                    high = h;
                } else {
                    low = h;
                }
            }
            left[cur] = high + cur - curind;

            low = curind - 1;
            high = count(nodes[cur]);
            while (high - low > 1) {
                int h = high + low >>> 1;
                if (get(nodes[cur], h).v - cur == h - (curind - 1)) {
                    low = h;
                } else {
                    high = h;
                }
            }
            right[cur] = low + cur - curind + 1;

            nodes[cur] = insertb(nodes[cur], new Node(cur));
            if (parents[cur] != -1) {
                if (count(nodes[cur]) > count(nodes[parents[cur]])) {
                    Node d = nodes[cur];
                    nodes[cur] = nodes[parents[cur]];
                    nodes[parents[cur]] = d;
                }
                // drain
                while (nodes[cur] != null) {
                    Node first = get(nodes[cur], 0);
                    nodes[cur] = erase(nodes[cur], 0);
                    nodes[parents[cur]] = insertb(nodes[parents[cur]], first);
                }
            }
        }
        int[][] rs = new int[n][];
        int[][] rs2 = new int[n][];
        int q = 0, q2 = 0;
        for (int i = 0; i < n; i++) {
            if (right[i] - i >= i - left[i]) {
                rs[q++] = new int[] { left[i], i, right[i] };
            } else {
                rs2[q2++] = new int[] { right[i], i, left[i] };
            }
        }
        long result = 0;
        result += calculate(Arrays.copyOf(rs, q), n, parents);
        for (int i = 0; i < q2; i++) {
            rs2[i][0] = n - 1 - rs2[i][0];
            rs2[i][1] = n - 1 - rs2[i][1];
            rs2[i][2] = n - 1 - rs2[i][2];
        }
        for (int i = 0; i < n; i++)
            parents[i] = n - 1 - parents[i];
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int d = parents[i];
            parents[i] = parents[j];
            parents[j] = d;
        }
        for (int i = 0, j = q2 - 1; i < j; i++, j--) {
            int[] d = rs2[i];
            rs2[i] = rs2[j];
            rs2[j] = d;
        }
        result += calculate(Arrays.copyOf(rs2, q2), n, parents);
        out.println(result);
    }

    long calculate(int[][] rs, int n, int[] par) {
        int m = rs.length;
        SegmentTreeRMQ stmin = new SegmentTreeRMQ(par);
        int[] stack = new int[n];
        long[] has = new long[n + 1];
        long[] leftHas = new long[n + 1];
        int sp = 0;
        int pre = n - 1;
        int[] leftStack = new int[n];
        int[] lvals = new int[n];
        Arrays.fill(stack, -1);
        Arrays.fill(leftStack, -1);
        long ret = 0;
        for (int z = m - 1; z >= 0; z--) {
            int i = rs[z][1];
            int li = rs[z][0];
            int ri = rs[z][2];
            while (pre > i) {
                while (sp > 0 && par[pre] >= par[stack[sp - 1]])
                    sp--;
                int ll = Math.max(pre, par[pre]);
                int rr = sp >= 1 ? stack[sp - 1] : n;
                has[sp + 1] = Math.max(0, rr - ll) + has[sp];
                stack[sp++] = pre;
                pre--;
            }

            int tsp = sp;
            int lsp = 0;

            int lmin = i;
            for (int j = i; j >= li; j--) {
                int pj = j == i ? j : par[j];
                while (lsp > 0 && pj >= lvals[lsp - 1])
                    lsp--;
                if (lsp == 0) {
                    while (tsp > 0 && pj >= par[stack[tsp - 1]])
                        tsp--;

                    lvals[lsp] = pj;
                    int ll = Math.max(pre, pj);
                    int rr = tsp >= 1 ? stack[tsp - 1] : n;
                    leftHas[lsp + 1] = Math.max(0, rr - ll) + leftHas[lsp];
                    leftStack[lsp++] = j;
                } else {
                    int ll = Math.max(pre, pj);
                    int rr = lsp >= 1 ? leftStack[lsp - 1] : tsp >= 1 ? stack[tsp - 1] : n;
                    lvals[lsp] = pj;
                    leftHas[lsp + 1] = Math.max(0, rr - ll) + leftHas[lsp];
                    leftStack[lsp++] = j;
                }

                lmin = Math.min(lmin, pj);

                if (lmin >= j) {
                    int fl = stmin.firstle(i + 1, j - 1);
                    if (fl == -1) {
                        fl = ri + 1;
                    }
                    int lright = Math.min(ri, fl - 1);
                    if (tsp - 1 >= 0 && lright >= stack[tsp - 1]) {
                        int ub = upperBoundR(stack, 0, tsp, lright);
                        int ll = Math.max(stack[ub], par[stack[ub]]);
                        int rr = lright + 1;
                        long valid = leftHas[lsp] + has[tsp] - has[ub + 1] + Math.max(0, rr - ll);
                        ret += valid;
                    } else {
                        int ub = upperBoundR(leftStack, 0, lsp, lright);
                        int ll = Math.max(leftStack[ub], lvals[ub]);
                        int rr = lright + 1;
                        long valid = leftHas[lsp] - leftHas[ub + 1] + Math.max(0, rr - ll);
                        ret += valid;
                    }
                }
            }
        }
        return ret;

    }

    public static int upperBoundR(int[] a, int l, int r, int v) {
        int low = l - 1, high = r;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] <= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    public static class SegmentTreeRMQ {
        public int M, H, N;
        public int[] st;

        public SegmentTreeRMQ(int n) {
            N = n;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >>> 1;
            st = new int[M];
            Arrays.fill(st, 0, M, Integer.MAX_VALUE);
        }

        public SegmentTreeRMQ(int[] a) {
            N = a.length;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >>> 1;
            st = new int[M];
            for (int i = 0; i < N; i++) {
                st[H + i] = a[i];
            }
            Arrays.fill(st, H + N, M, Integer.MAX_VALUE);
            for (int i = H - 1; i >= 1; i--)
                propagate(i);
        }

        public void update(int pos, int x) {
            st[H + pos] = x;
            for (int i = (H + pos) >>> 1; i >= 1; i >>>= 1)
                propagate(i);
        }

        private void propagate(int i) {
            st[i] = Math.min(st[2 * i], st[2 * i + 1]);
        }

        public int minx(int l, int r) {
            if (l >= r)
                return 0;
            int min = Integer.MAX_VALUE;
            while (l != 0) {
                int f = l & -l;
                if (l + f > r)
                    break;
                int v = st[(H + l) / f];
                if (v < min)
                    min = v;
                l += f;
            }

            while (l < r) {
                int f = r & -r;
                int v = st[(H + r) / f - 1];
                if (v < min)
                    min = v;
                r -= f;
            }
            return min;
        }

        public int min(int l, int r) {
            return l >= r ? 0 : min(l, r, 0, H, 1);
        }

        private int min(int l, int r, int cl, int cr, int cur) {
            if (l <= cl && cr <= r) {
                return st[cur];
            } else {
                int mid = cl + cr >>> 1;
                int ret = Integer.MAX_VALUE;
                if (cl < r && l < mid) {
                    ret = Math.min(ret, min(l, r, cl, mid, 2 * cur));
                }
                if (mid < r && l < cr) {
                    ret = Math.min(ret, min(l, r, mid, cr, 2 * cur + 1));
                }
                return ret;
            }
        }

        public int firstle(int l, int v) {
            if (l >= N)
                return -1;
            int cur = H + l;
            while (true) {
                if (st[cur] <= v) {
                    if (cur < H) {
                        cur = 2 * cur;
                    } else {
                        return cur - H;
                    }
                } else {
                    cur++;
                    if ((cur & cur - 1) == 0)
                        return -1;
                    if ((cur & 1) == 0)
                        cur >>>= 1;
                }
            }
        }

        public int lastle(int l, int v) {
            int cur = H + l;
            while (true) {
                if (st[cur] <= v) {
                    if (cur < H) {
                        cur = 2 * cur + 1;
                    } else {
                        return cur - H;
                    }
                } else {
                    if ((cur & cur - 1) == 0)
                        return -1;
                    cur--;
                    if ((cur & 1) == 1)
                        cur >>>= 1;
                }
            }
        }
    }

    public static Random gen = new Random(0);

    static public class Node {
        public int v;
        public long priority;
        public Node left, right, parent;

        public int count;

        public Node(int v) {
            this.v = v;
            priority = gen.nextLong();
            update(this);
        }

    }

    public static Node update(Node a) {
        if (a == null)
            return null;
        a.count = 1;
        if (a.left != null)
            a.count += a.left.count;
        if (a.right != null)
            a.count += a.right.count;

        return a;
    }

    public static void propagate(Node x) {
        for (; x != null; x = x.parent)
            update(x);
    }

    public static Node disconnect(Node a) {
        if (a == null)
            return null;
        a.left = a.right = a.parent = null;
        return update(a);
    }

    public static Node root(Node x) {
        if (x == null)
            return null;
        while (x.parent != null)
            x = x.parent;
        return x;
    }

    public static int count(Node a) {
        return a == null ? 0 : a.count;
    }

    public static void setParent(Node a, Node par) {
        if (a != null)
            a.parent = par;
    }

    public static Node merge(Node a, Node b, Node... c) {
        Node x = merge(a, b);
        for (Node n : c)
            x = merge(x, n);
        return x;
    }

    public static Node merge(Node a, Node b) {
        if (b == null)
            return a;
        if (a == null)
            return b;
        if (a.priority > b.priority) {
            setParent(a.right, null);
            setParent(b, null);
            a.right = merge(a.right, b);
            setParent(a.right, a);
            return update(a);
        } else {
            setParent(a, null);
            setParent(b.left, null);
            b.left = merge(a, b.left);
            setParent(b.left, b);
            return update(b);
        }
    }

    public static Node[] split(Node x) {
        if (x == null)
            return new Node[] { null, null };
        if (x.left != null)
            x.left.parent = null;
        Node[] sp = new Node[] { x.left, x };
        x.left = null;
        update(x);
        while (x.parent != null) {
            Node p = x.parent;
            x.parent = null;
            if (x == p.left) {
                p.left = sp[1];
                if (sp[1] != null)
                    sp[1].parent = p;
                sp[1] = p;
            } else {
                p.right = sp[0];
                if (sp[0] != null)
                    sp[0].parent = p;
                sp[0] = p;
            }
            update(p);
            x = p;
        }
        return sp;
    }

    public static Node[] split(Node a, int... ks) {
        int n = ks.length;
        if (n == 0)
            return new Node[] { a };
        for (int i = 0; i < n - 1; i++) {
            if (ks[i] > ks[i + 1])
                throw new IllegalArgumentException(Arrays.toString(ks));
        }

        Node[] ns = new Node[n + 1];
        Node cur = a;
        for (int i = n - 1; i >= 0; i--) {
            Node[] sp = split(cur, ks[i]);
            cur = sp[0];
            ns[i] = sp[0];
            ns[i + 1] = sp[1];
        }
        return ns;
    }

    public static Node[] split(Node a, int K) {
        if (a == null)
            return new Node[] { null, null };
        if (K <= count(a.left)) {
            setParent(a.left, null);
            Node[] s = split(a.left, K);
            a.left = s[1];
            setParent(a.left, a);
            s[1] = update(a);
            return s;
        } else {
            setParent(a.right, null);
            Node[] s = split(a.right, K - count(a.left) - 1);
            a.right = s[0];
            setParent(a.right, a);
            s[0] = update(a);
            return s;
        }
    }

    public static Node insertb(Node root, Node x) {
        int ind = search(root, x.v);
        if (ind < 0) {
            ind = -ind - 1;
        }
        return insert(root, ind, x);
    }

    public static Node insert(Node a, int K, Node b) {
        if (a == null) {
            return b;
        }
        if (b.priority < a.priority) {
            if (K <= count(a.left)) {
                a.left = insert(a.left, K, b);
                setParent(a.left, a);
            } else {
                a.right = insert(a.right, K - count(a.left) - 1, b);
                setParent(a.right, a);
            }
            return update(a);
        }
        Node[] ch = split(a, K);
        b.left = ch[0];
        b.right = ch[1];
        setParent(b.left, b);
        setParent(b.right, b);
        return update(b);
    }

    public static Node erase(Node a, int K) {
        if (a == null)
            return null;
        if (K < count(a.left)) {
            a.left = erase(a.left, K);
            setParent(a.left, a);
            return update(a);
        } else if (K == count(a.left)) {
            setParent(a.left, null);
            setParent(a.right, null);
            Node aa = merge(a.left, a.right);
            disconnect(a);
            return aa;
        }
        a.right = erase(a.right, K - count(a.left) - 1);
        setParent(a.right, a);
        return update(a);
    }

    public static Node get(Node a, int K) {
        while (a != null) {
            if (K < count(a.left)) {
                a = a.left;
            } else if (K == count(a.left)) {
                break;
            } else {
                K = K - count(a.left) - 1;
                a = a.right;
            }
        }
        return a;
    }

    public static int index(Node a) {
        if (a == null) {
            return -1;
        }
        int ind = count(a.left);
        while (a != null) {
            Node par = a.parent;
            if (par != null && par.right == a) {
                ind += count(par.left) + 1;
            }
            a = par;
        }
        return ind;
    }

    public static int search(Node a, int q) {
        int lcount = 0;
        while (a != null) {
            if (a.v == q) {
                lcount += count(a.left);
                break;
            }
            if (q < a.v) {
                a = a.left;
            } else {
                lcount += count(a.left) + 1;
                a = a.right;
            }
        }
        if (a == null) {
            return -(lcount + 1);
        }
        return lcount;
    }

    public static Node next(Node x) {
        if (x == null)
            return null;
        if (x.right != null) {
            x = x.right;
            while (x.left != null) {
                x = x.left;
            }
            return x;
        } else {
            while (true) {
                Node p = x.parent;
                if (p == null) {
                    return null;
                }
                if (p.left == x) {
                    return p;
                }
                x = p;
            }
        }
    }

    public static Node prev(Node x) {
        if (x == null)
            return null;
        if (x.left != null) {
            x = x.left;
            while (x.right != null) {
                x = x.right;
            }
            return x;
        } else {
            while (true) {
                Node p = x.parent;
                if (p == null) {
                    return null;
                }
                if (p.right == x) {
                    return p;
                }
                x = p;
            }
        }
    }

    public static Node[] nodes(Node a) {
        return nodes(a, new Node[a.count], 0, a.count);
    }

    public static Node[] nodes(Node a, Node[] ns, int L, int R) {
        if (a == null)
            return ns;
        nodes(a.left, ns, L, L + count(a.left));
        ns[L + count(a.left)] = a;
        nodes(a.right, ns, R - count(a.right), R);
        return ns;
    }

    public static int[][] makeData(int[][] g, int root) {
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[] depth = new int[n];
        depth[0] = 0;

        int[] q = new int[n];
        q[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = q[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    q[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][] { par, q, depth };
    }

    static int[][] makeGraph(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from) {
            p[f]++;
        }
        for (int t : to) {
            p[t]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[p[i]];
        }
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        new selfdriving().run();
    }

    private byte[] inbuf = new byte[1024];
    private int lenbuf = 0, ptrbuf = 0;

    private int readByte() {
        if (lenbuf == -1)
            throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0)
                return -1;
        }
        return inbuf[ptrbuf++];
    }

    private int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
            ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

}