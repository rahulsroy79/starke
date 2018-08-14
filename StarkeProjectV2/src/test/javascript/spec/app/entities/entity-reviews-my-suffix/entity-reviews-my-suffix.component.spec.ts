/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Starkev2TestModule } from '../../../test.module';
import { EntityReviewsMySuffixComponent } from 'app/entities/entity-reviews-my-suffix/entity-reviews-my-suffix.component';
import { EntityReviewsMySuffixService } from 'app/entities/entity-reviews-my-suffix/entity-reviews-my-suffix.service';
import { EntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityReviewsMySuffix Management Component', () => {
        let comp: EntityReviewsMySuffixComponent;
        let fixture: ComponentFixture<EntityReviewsMySuffixComponent>;
        let service: EntityReviewsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityReviewsMySuffixComponent],
                providers: []
            })
                .overrideTemplate(EntityReviewsMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EntityReviewsMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityReviewsMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EntityReviewsMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.entityReviews[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
