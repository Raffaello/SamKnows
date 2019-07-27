csv <- read.csv("data/data_engineering.csv")

## unique entries and duplicates

uniques <- unique(csv)
duplicates <- csv[duplicated(csv),]


# pie chart
successes <- sum(uniques$successes)
failures <- sum(uniques$failures)
slices <- c(successes, failures)
labels <- c("Successes", "Failures")
pct <- round(slices/sum(slices)*100)
labels <- paste(labels, pct)
labels <- paste(labels, "%", sep="")
pie(slices, labels, main="Success Ratio")


## when failed on average?

fails <- uniques[uniques$failures == 1,]
fails_ratio <- fails$bytes_total / fails$bytes_per_second
fails_ratio[is.nan(fails_ratio)] <- 0
mean_fails <- mean(fails_ratio)
