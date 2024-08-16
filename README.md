# Springboot-prometheus-example

I have created a simple project with spring-boot to publish metrics into Prometheus. There are three simple endpoints available on this application:
`/push` which will generate an increment in the queue size
`/pop` which will decrement the queue size 
`/hello` example for request count. 

To run this project, simply run `docker-compose up`. It will setup Prometheus and the application. I am providing Prometheus configuration using prometheus.yml file
```yaml
global:
  scrape_interval: 5s
scrape_configs:
  - job_name: 'application'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['application:8080']
```
Here I have defined the metrics scrape interval and the job name `application,` including metrics path `/actuator/prometheus`, which is the path for the sending metrics by the Prometheus client used by spring-boot. For the ruby Prometheus gem it should be like this
```yaml
global:
  scrape_interval: "15s"

scrape_configs:
  - job_name: "prometheus"
    static_configs:
      - targets:
        - "localhost:9090"
```
For detail information, please check [this](https://github.com/prometheus/client_ruby) repo
after running the application; just browse a couple of times in `localhost:8080/push` or `localhost:8080/pop`, and you will see queue size change metrics in Prometheus
by browsing `localhost:9090` and the executing this promql query `queue_size{queue_name="medstack-example"}`. If you browse `/hello', then you can check the number of requests by executing `request_count` query. 
