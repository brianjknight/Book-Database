# Bookstore Database

## Introduction
I like to read and use the Goodreads app/website to track my reading. It allows you to keep track of books you have read, are currently reading, and want to read in the future. You can rate books you have read visit a Recommendations page for suggestions on what to read next. 

The purpose of this project is to gain experience with APIs (Spring Boot) and SQL databases (Postgres)

## Problem Statement
I sometimes have trouble deciding on my next book to read. Rather than using Goodreads recommendations, I often find myself using specific Google searches such as "best space sci-fi books all-time" or "top tech thriller book of the last 15 years" depending on my mood at the time. I end up spending a lot of time browsing lists from different sources unable to make a decision based on someone else's subjective opionions. I would like a way to run a more specialized search depending on my mood at the time. 

## Solution
Download the Goodreads book dataset and create a custom recommendation engine. The API can include specific filters like avg. rating, number of ratings, number of pages, year published, genre, author, awards, etc. so that I can create a specialized search.

## Technologies
- Postgres (use multiple tables for interacting with a relational database)
- Docker
- JPA
- Spring Boot

## Resources
- Goodreads Dataset   
*If you are using our datasets, please cite the following papers:
Mengting Wan, Julian McAuley, "Item Recommendation on Monotonic Behavior Chains", in RecSys'18.  [bibtex]
Mengting Wan, Rishabh Misra, Ndapa Nakashole, Julian McAuley, "Fine-Grained Spoiler Detection from Large-Scale Review Corpora", in ACL'19. [bibtex]*

  - https://sites.google.com/eng.ucsd.edu/ucsdbookgraph/home
  - https://github.com/MengtingWan/goodreads


- My Goodreads data https://help.goodreads.com/s/article/How-do-I-get-a-copy-of-my-data-from-Goodreads
- Searching And Filtering Using JPA Specification - Spring Boot
  - https://blog.piinalpin.com/2022/04/searching-and-filtering-using-jpa-specification/#using-search-specification
