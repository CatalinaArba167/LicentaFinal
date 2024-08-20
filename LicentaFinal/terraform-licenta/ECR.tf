resource "aws_ecr_repository" "terraform_ecr_repo_baclend" {
  name                 = "terraform_ecr_repo_backend"

  #Configuration block that defines image scanning configuration for the repository. By default, image scanning must be manually triggered. See the ECR User Guide for more information about image scanning.
  #scan_on_push - (Required) Indicates whether images are scanned after being pushed to the repository (true) or not scanned (false).
  image_scanning_configuration {
    scan_on_push = true
  }
}


resource "aws_ecr_repository" "terraform_ecr_repo_ai" {
  name                 = "terraform_ecr_repo_ai"

  #Configuration block that defines image scanning configuration for the repository. By default, image scanning must be manually triggered. See the ECR User Guide for more information about image scanning.
  #scan_on_push - (Required) Indicates whether images are scanned after being pushed to the repository (true) or not scanned (false).
  image_scanning_configuration {
    scan_on_push = true
  }
}
