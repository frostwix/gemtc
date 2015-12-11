regressionParams <- function(regressor, nt, nc=0) {
  if (!is.null(regressor[['control']])) {
    control <- as.numeric(regressor[['control']])
    betas <- paste0('beta[', (1:nt)[-control], ']')
    regression.parameters <- list('shared'='B',
                                  'unrelated'=betas,
                                  'exchangeable'=c(betas, 'B', 'reg.sd'))
    regression.parameters[[regressor[['coefficient']]]]
  } else { # by class
    paste0('B[', 2:nc, ']')
  }
}

regressionClassMap <- function(classes) {
  trt.to.class <- rep(NA, sum(sapply(classes, length)))
  for (i in 1:length(classes)) {
    trt.to.class[as.numeric(classes[[i]])] <- i
  }
  trt.to.class
}

regressionAdjustMatrix <- function(t1, t2, regressor, nt) {
  nparams <- length(regressionParams(regressor, nt))
  pairs <- treatment.pairs(t1, t2, 1:nt)

  if (!is.null(regressor[['classes']])) {
    pairs <- matrix(regressionClassMap(regressor[['classes']])[pairs], nrow=nrow(pairs))
    control <- 1
  } else {
    control <- as.numeric(regressor[['control']])
  }

  betaIndex <- function(i) {
    if (i > control) i - 1 else i
  }
  transform <- apply(pairs, 1, function(pair) {
    v <- rep(0, nparams)
    t1 <- pair[1]
    t2 <- pair[2]
    if (regressor[['coefficient']] == 'shared') {
      if (t1 == control && t2 != control) {
        v[1] <- 1
      } else if (t1 != control && t2 == control) {
        v[1] <- -1
      }
    } else {
      if (t1 == control && t2 != control) {
        v[betaIndex(t2)] <- 1
      } else if (t1 != control && t2 == control) {
        v[betaIndex(t1)] <- -1
      } else if (t1 != control && t2 != control && t1 != t2) {
        v[betaIndex(t1)] <- -1
        v[betaIndex(t2)] <- 1
      }
    }
    v
  })
  matrix(transform, ncol=nrow(pairs))
}