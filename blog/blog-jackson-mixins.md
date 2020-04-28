# Jackson mix-ins in Java

Today we are going to look at annotation mix-in feature of Jackson. 

## Why should I care?

Jackson mix-ins are crucial to clearly separate application layers according to
[onion architecture](https://jeffreypalermo.com/2008/07/the-onion-architecture-part-1/) 
(popularised further 
[here](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)), 
without causing code duplication.



Another important use case would be if you do not control source code for 
serialised objects, but rather import them as library. In this case it is 
impossible to annotate class and it will fail to serialize properly if any of 
methods do not follow Java property convention.

## Results

#### Lowest latencies possible

I made these measurements as I was curious how synchronous commit affects 
latency, and it's roughly 30% overhead.

#### Fault-tolerant setup with synchronous consumer commit

For the higher throughput scenario I had to turn batching on and put a setting
to batch at most 500 records. Otherwise maximum throughput would be 1000 /
MIN_LATENCY = 1000 / 5 = 200 messages/s


## Conclusion

Looks like these articles about huge latencies of 100ms magnitude relate to
older versions of Kafka. Replicated operation showed pretty decent results,
which should be enough for most cases. Please note that these results are not
made on dedicated hardware and I didn't do any memory/OS settings/affinity
tuning, so very likely it's possible to get even better results.  

There are no measurements for durable messages (force flush each message on 
disk) and I think tests for such setup are extremely hardware dependent and 
should be made using proper HDD/SSD.

My takeaway from this is that unless you need sub-millisecond latencies I 
could do with Kafka. Unless you are in that outlier part of spectrum 
technologies/solution selection must be done based on functionality it provides
and not on specific performance parameters. And of course reusing well-known 
technology is much better than having a zoo of trendy names, that barely anyone
in team understands properly.
