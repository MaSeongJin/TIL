## 플로이드-워셜 알고리즘
플로이드-워셜 알고리즘은 음수 사이클이 없는 그래프내의 각 모든 정점에서 각 모든 정점에 까지의 최단거리를 모두 구할 수 있는 알고리즘이다. 다익스트라 알고리즘과는 다르게 그래프에 음수 사이클만 존재하지 않으면, 음의 가중치를 갖는 간선이 존재해도 상관이 없다는 것이다.
- 음수 사이클 : 사이클의 모든 경로를 지나 원래 지점으로 돌아 왔을때, 최종적인 비용이 음수가 되는 경우를 말한다.

플로이드-워셜 알고리즘은 다이나믹 프로그래밍 기법을 사용한 알고리즘이고, 인접 행렬을 이용하여 각 노드간 최소 비용을 계산한다.

또한, 모든 노드에서, 모든 노드로 가는 간선의 개수가 0개에서 N개(총 노드의 개수 만큼 간선을 선택)까지 몇 개의 간선을 거쳐서 해당 노드로 사는지를 모두 고려한다.

## 플로이드-워셜 알고리즘 방식
<p align="center"><img src="https://i.postimg.cc/XY29qcWy/img1-daumcdn.png"></p>

우선, 0개의 간선을 거쳐서 모든 노드에서 모든 노드로 가는 경우를 생각해보자. 0개의 간선을 거쳐서 모든 노드에서 모든 노드로 가는 방법은 자기 자신에서 자기 자신의 노드로 가는 경우 밖에 없다. 따라서, 초기 인접 행렬의 값은 자기 자신에서 자기 자신으로 가는 경우(=0)를 제외하고는 다른 노드로 갈 수 있는 방법이 없으므로 모두 매우 큰값으로 초기화해준다(N개의 정점을 모두 거쳐서 해당 정점으로 가더라도 그것보다 더 큰 값으로 초기화 해주어야 한다). 그렇다면 인접행렬은 아래와 같은 값을 가지게 될 것이다(큰 값은 INF로 표시한다).
<p align="center"><img src="https://i.postimg.cc/R054c2vr/img1-daumcdn.png"></p>

그 다음은 1개의 간선을 거쳐서 모든 노드에서 노드로 가는 경우이다. 1개의 간선을 거쳐서 모든 노드로 가는 방법은, 말 그대로 주어진 그래프의 간선의 연결상태를 고려하라는 것이다. 물론 노드에서 노드로가는 간선이 여러가지인 경우가 존재한다면, 그 간선 중 최소 비용인 것을 선택하면 된다. 보통 여기까지가 플로이드 관련 알고리즘 문제에서 초기화 단계에 속한다. 즉, 위 그림 그대로 간선의 연결 상태를 고려하여 인접행렬을 초기화하면 다음과 같다.
<p align="center"><img src="https://i.postimg.cc/vZVkrzYC/img1-daumcdn.png"></p>
  
이렇게 간선 0개를 거친 경우, 1개를 거친 경우, ..., N개를 거친 경우의 최소 거리 비용을 비교하면서 인접행렬(각 정점에서 정점까지의 거리 비용을 나타냄)을 갱신하는 것이 플로이드 알고리즘의 매커니즘이다. 어차피 연결되어 있지 않는 노드는 초기에 매우 큰값(INF)으로 초기화되어 있을 것이고, 간선의 개수를 차례로 고려하면서 최소 비용이 고려될 것이므로 노드에서 노드로 가는 거리는 항상 최소인 상태를 유지할 것이다.

그 다음은 2개의 간선을 거쳐서 모든 노드에서 노드로 가는 경우이다. 2개의 간선을 거쳐서 다른 노드로 가는 것을 고려하기 가장 쉬운 방법은 무엇일까? 여기에서 플로이드 알고리즘의 구현 아이디어를 얻을 수 있는데, 그것은 바로 특정 노드을 거쳐서 해당 노드로 가는 것이다. 예를 들어 아래 그림처럼 0번 정점을 기준으로 2번 노드에 대해서 생각해보면 다음과 같다.
<p align="center"><img src="https://i.postimg.cc/3rpHCYtt/img1-daumcdn.png"></p>

2번 노드에서 0번 노드를 거쳐서 0번 노드로 가는 방법(2개의 간선을 지난다). 이때 값은 7이므로, 기존 값과 다른점이 없으므로 갱신이 일어나지 않는다.
<p align="center"><img src="https://i.postimg.cc/rwHhPtmx/img1-daumcdn.png"></p>

2번 노드에서 0번 노드를 거쳐서 1번노드로 가는법. 이때 총 비용은 12이고, 기존의 비용은 3이므로 갱신이 일어나지 않는다.
<p align="center"><img src="https://i.postimg.cc/0Npp06J8/img1-daumcdn.png"></p>
  
2번 노드에서 0번 노드를 거쳐서 2번노드로 가는법. 이때 총 비용은 14이고, 기존의 비용은 0이므로 갱신이 일어나지 않는다.
<p align="center"><img src="https://i.postimg.cc/fLPT3CTk/img1-daumcdn.png"></p>
  
2번 노드에서 0번 노드를 거쳐서 3번노드로 가는법. 이때 총 비용은 9이고, 기존의 비용은 10이므로 9로 갱신된다.
<p align="center"><img src="https://i.postimg.cc/QN5H9L13/img1-daumcdn.png"></p>

2번 노드에서 0번 노드를 거쳐서 4번노드로 가는법. 이때 총 비용은 8이고, 기존의 비용은 INF(갈수 없음)이므로 8로 갱신된다!

즉, 그림과 같은 방법을 모든 노드에 대해서 똑같이 고려해주는 것이다. 이렇게 N개의 간선, 즉, N개의 노드를 거쳐서 가는 경우까지 차례로 모두 고려해 준다면, 간선 0개 ~ N개를 거쳐서 해당 노드로 가는 비용 중 최솟값을 모두 고려가 가능하다는 것을 알 수 있다. 위의 그림을 바탕으로 2번 노드의 값들이 어떻게 갱신되었는지 확인해보자.
<p align="center"><img src="https://i.postimg.cc/c4FZ6T9W/img1-daumcdn.png"></p>

## 플로이드-워셜 알고리즘 구현
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
sample input(첫 번째 숫자는 노드의 개수, 두 번째 숫자는 간선의 개수 이다).
5
8
0 1 5
0 4 1
0 2 7
0 3 2
1 2 3
1 3 6
2 3 10
3 4 4
 */
public class 플로이드 {
	static int N, M;
	static int[][] dist;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		// 플로이드 초기 거리 테이블 초기화
		dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 자기 자신으로 가는 길은 최소 비용이 0이다.
				if (i == j) {
					dist[i][j] = 0;
					continue;
				}
				// 자기 자신으로 가는 경우를 제외하고는 매우 큰 값(N개의 노드를 모두 거쳐서 가더라도 더 큰 값).
				dist[i][j] = 100_000_000;
			}
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// 가는 경로가 하나가 아닐 수 있다. 따라서 그 중 최소 비용을 저장해두면 된다.
			dist[a][b] = Math.min(dist[a][b], cost);
			dist[b][a] = Math.min(dist[b][a], cost);
		}

		// 플로이드 워셜 알고리즘
		// 노드를 1개부터 N개까지 거쳐가는 경우를 모두 고려한다.
		for (int k = 0; k < N; k++) {
			// 노드 i에서 j로 가는 경우.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// k번째 노드를 거쳐가는 비용이 기존 비용보다 더 작은 경우 갱신
					// 또는 연결이 안되어있던 경우(INF) 연결 비용 갱신.
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 연결이 안되어 있는 경우
				if (dist[i][j] == 100_000_000) {
					System.out.print("INF ");
				} else {
					System.out.print(dist[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
```
### 결과
<p align="center"><img src="https://i.postimg.cc/jqQn305h/img1-daumcdn.png"></p>

### 시간복잡도
구현을 확인하면 알 수 있겠지만, 시간 복잡도를 구하는 방법은 매우 간단한다. 모든 노드(V)에 대해서, V x V 행렬을 갱신해주는 연산을 진행하므로 O(V^3)의 시간복잡도를 갖는다.
  
O(V^3)라는 시간 복잡도를 갖기 때문에, 입력의 크기가 100 정도만 되어도 백 만번의 연산이 수행되어야 한다. 따라서, 플로이드 알고리즘을 사용하는 경우는 입력의 크기를 주의깊게 살펴야한다.
