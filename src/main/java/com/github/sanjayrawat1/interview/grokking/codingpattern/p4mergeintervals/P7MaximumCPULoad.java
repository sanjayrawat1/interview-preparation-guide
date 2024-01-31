package com.github.sanjayrawat1.interview.grokking.codingpattern.p4mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * We are given a list of jobs. Each job has a start time and end time and a CPU load when it is running.
 * Our goal is to find the maximum CPU load at any time if all the jobs are running on the same machine.
 *
 * @author Sanjay Singh Rawat
 */
public class P7MaximumCPULoad {

    public static void main(String[] args) {
        assertThat(maxCpuLoad(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9,6))))
                .isEqualTo(7);
        assertThat(maxCpuLoad(Arrays.asList(new Job(6,7,10), new Job(2,4,11), new Job(8,12,15))))
                .isEqualTo(15);
        assertThat(maxCpuLoad(Arrays.asList(new Job(1, 4, 2), new Job(2,4,1), new Job(3,6,5))))
                .isEqualTo(8);

        assertThat(maxCpuLoadInPlace(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9,6))))
                .isEqualTo(7);
        assertThat(maxCpuLoadInPlace(Arrays.asList(new Job(6,7,10), new Job(2,4,11), new Job(8,12,15))))
                .isEqualTo(15);
        assertThat(maxCpuLoadInPlace(Arrays.asList(new Job(1, 4, 2), new Job(2,4,1), new Job(3,6,5))))
                .isEqualTo(8);
    }

    private static int maxCpuLoad(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return 0;
        }

        if (jobs.size() < 2) {
            return jobs.getFirst().cpuLoad;
        }

        jobs.sort(Comparator.comparingInt(value -> value.startTime));

        List<Job> mergedJobs = new ArrayList<>();
        int startTime = jobs.get(0).startTime;
        int endTime = jobs.get(0).endTime;
        int cpuLoad = jobs.get(0).cpuLoad;
        for (int i = 1; i < jobs.size(); i++) {
            Job current = jobs.get(i);

            if (current.startTime < endTime) {
                endTime = Math.max(endTime, current.endTime);
                cpuLoad += current.cpuLoad;
            } else {
                mergedJobs.add(new Job(startTime, endTime, cpuLoad));
                startTime = current.startTime;
                endTime = current.endTime;
                cpuLoad = current.cpuLoad;
            }
        }

        mergedJobs.add(new Job(startTime, endTime, cpuLoad));

        int maxCpuLoad = 0;
        for (Job job : mergedJobs) {
            maxCpuLoad = Math.max(maxCpuLoad, job.cpuLoad);
        }
        return maxCpuLoad;
    }

    private static int maxCpuLoadInPlace(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return 0;
        }

        if (jobs.size() < 2) {
            return jobs.getFirst().cpuLoad;
        }

        jobs.sort(Comparator.comparingInt(value -> value.startTime));
        int index = 0;

        for (int i = 1; i < jobs.size(); i++) {
            Job current = jobs.get(i);
            Job previous = jobs.get(i - 1);

            if (current.startTime < previous.endTime) {
                jobs.get(index).endTime = Math.max(current.endTime, previous.endTime);
                jobs.get(index).cpuLoad += current.cpuLoad;
            } else {
                index++;
                jobs.set(index, current);
            }
        }

        int maxCpuLoad = 0;
        for (int i = 0; i <= index; i++) {
            maxCpuLoad = Math.max(maxCpuLoad, jobs.get(i).cpuLoad);
        }
        return maxCpuLoad;
    }

    private static class Job {
        int startTime;
        int endTime;
        int cpuLoad;

        public Job(int startTime, int endTime, int cpuLoad) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.cpuLoad = cpuLoad;
        }
    }
}
